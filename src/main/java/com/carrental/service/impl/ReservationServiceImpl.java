
package com.carrental.service.impl;

import com.carrental.entity.Car;
import com.carrental.entity.Reservation;
import com.carrental.entity.User;
import com.carrental.repository.CarRepository;
import com.carrental.repository.ReservationRepository;
import com.carrental.repository.UserRepository;
import com.carrental.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired private ReservationRepository reservationRepository;
    @Autowired private CarRepository carRepository;
    @Autowired private UserRepository userRepository;

    @Override
    public Reservation createReservation(Long carId, Long userId, LocalDate startDate, LocalDate endDate) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car not found"));
        if (!car.isAvailable()) throw new RuntimeException("Car not available");

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        double cost = car.getRentalPricePerDay() * (endDate.toEpochDay() - startDate.toEpochDay());

        Reservation reservation = new Reservation();
        reservation.setCar(car);
        reservation.setUser(user);
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setTotalCost(cost);

        car.setAvailable(false);
        carRepository.save(car);
        return reservationRepository.save(reservation);
    }

    @Override
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new RuntimeException("Reservation not found"));
        Car car = reservation.getCar();
        car.setAvailable(true);
        carRepository.save(car);
        reservationRepository.delete(reservation);
    }

    @Override
    public List<Reservation> getReservationsByUser(Long userId) {
        return reservationRepository.findByUserId(userId);
    }
}
