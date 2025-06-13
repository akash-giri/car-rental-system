package com.carrental.service.impl;

import com.carrental.entity.Car;
import com.carrental.repository.CarRepository;
import com.carrental.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> getAvailableCars() {
        return carRepository.findByAvailableTrue();
    }

    @Override
    public List<Car> searchCars(String make, double minPrice, double maxPrice) {
        return carRepository.findByMakeContainingIgnoreCaseAndRentalPricePerDayBetweenAndAvailableTrue(make, minPrice, maxPrice);
    }

    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
    }

    // Admin Methods
    @Override
    public Car addCar(Car car) {
        car.setAvailable(true); // Default to available
        return carRepository.save(car);
    }

    @Override
    public Car updateCar(Long id, Car updatedCar) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        car.setMake(updatedCar.getMake());
        car.setModel(updatedCar.getModel());
        car.setYear(updatedCar.getYear());
        car.setLicensePlate(updatedCar.getLicensePlate());
        car.setCarType(updatedCar.getCarType());
        car.setAvailable(updatedCar.isAvailable());
        car.setRentalPricePerDay(updatedCar.getRentalPricePerDay());

        return carRepository.save(car);
    }

    @Override
    public void deleteCar(Long id) {
        if (!carRepository.existsById(id)) {
            throw new RuntimeException("Car not found with ID: " + id);
        }
        carRepository.deleteById(id);
    }
}
