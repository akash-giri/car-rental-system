
package com.carrental.controller;

import com.carrental.entity.Car;
import com.carrental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @GetMapping("/available")
    public List<Car> getAvailableCars() {
        return carRepository.findByAvailableTrue();
    }

    @GetMapping("/search")
    public List<Car> searchCars(@RequestParam String carType,
                                @RequestParam double minPrice,
                                @RequestParam double maxPrice) {
        return carRepository.findByAvailableTrueAndCarTypeContainingIgnoreCaseAndRentalPricePerDayBetween(carType, minPrice, maxPrice);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Car addCar(@RequestBody Car car) {
        return carRepository.save(car);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Car updateCar(@PathVariable Long id, @RequestBody Car updatedCar) {
        Car car = carRepository.findById(id).orElseThrow();
        car.setMake(updatedCar.getMake());
        car.setModel(updatedCar.getModel());
        car.setYear(updatedCar.getYear());
        car.setLicensePlate(updatedCar.getLicensePlate());
        car.setRentalPricePerDay(updatedCar.getRentalPricePerDay());
        car.setCarType(updatedCar.getCarType());
        car.setAvailable(updatedCar.isAvailable());
        return carRepository.save(car);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCar(@PathVariable Long id) {
        carRepository.deleteById(id);
    }
}
