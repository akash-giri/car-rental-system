
package com.carrental.controller;

import com.carrental.entity.Reservation;
import com.carrental.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public Reservation createReservation(@RequestParam Long carId,
                                         @RequestParam Long userId,
                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return reservationService.createReservation(carId, userId, startDate, endDate);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    public void cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/{userId}")
    public List<Reservation> getUserReservations(@PathVariable Long userId) {
        return reservationService.getReservationsByUser(userId);
    }
}
