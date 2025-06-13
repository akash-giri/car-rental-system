
package com.carrental.service;

import com.carrental.entity.Car;
import java.util.List;

public interface CarService {
    List<Car> getAllCars();
    List<Car> getAvailableCars();
    List<Car> searchCars(String C, double minPrice, double maxPrice);
    Car getCarById(Long id);
    Car addCar(Car car);
    Car updateCar(Long id, Car car);
    void deleteCar(Long id);
}
