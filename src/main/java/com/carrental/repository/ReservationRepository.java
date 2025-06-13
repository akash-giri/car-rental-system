
package com.carrental.repository;

import com.carrental.entity.Reservation;
import com.carrental.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByCarAndStatusAndEndDateAfterAndStartDateBefore(Car car, String status, LocalDate start, LocalDate end);
    List<Reservation> findByUserId(Long userId);
}
