
package com.carrental.service;

import com.carrental.entity.Reservation;
import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    Reservation createReservation(Long carId, Long userId, LocalDate startDate, LocalDate endDate);
    void cancelReservation(Long reservationId);
    List<Reservation> getReservationsByUser(Long userId);
}
