
package com.carrental.service;

import com.carrental.entity.Payment;

public interface PaymentService {
    Payment processPayment(Long reservationId, String paymentMethod);
}
