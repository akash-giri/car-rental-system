
package com.carrental.controller;

import com.carrental.entity.Payment;
import com.carrental.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public Payment makePayment(@RequestParam Long reservationId,
                               @RequestParam String paymentMethod) {
        return paymentService.processPayment(reservationId, paymentMethod);
    }
}
