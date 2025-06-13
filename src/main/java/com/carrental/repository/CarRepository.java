package com.carrental.repository;

import com.carrental.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByAvailableTrue();

    List<Car> findByCarTypeContainingIgnoreCase(String carType);

    List<Car> findByMakeContainingIgnoreCaseAndRentalPricePerDayBetweenAndAvailableTrue(
            String make, double minPrice, double maxPrice
    );

    List<Car> findByAvailableTrueAndCarTypeContainingIgnoreCaseAndRentalPricePerDayBetween(
            String carType, double min, double max
    );
}
