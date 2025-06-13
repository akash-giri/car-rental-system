
package com.carrental.service.impl;

import com.carrental.entity.Payment;
import com.carrental.entity.Reservation;
import com.carrental.repository.PaymentRepository;
import com.carrental.repository.ReservationRepository;
import com.carrental.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired private PaymentRepository paymentRepository;
    @Autowired private ReservationRepository reservationRepository;

    @Override
    public Payment processPayment(Long reservationId, String paymentMethod) {
        Reservation reservation = reservationRepository.findById(reservationId)
            .orElseThrow(() -> new RuntimeException("Reservation not found"));

        Payment payment = new Payment();
        payment.setReservation(reservation);
        payment.setAmount(reservation.getTotalCost());
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentDate(LocalDateTime.now());

        return paymentRepository.save(payment);
    }
}
