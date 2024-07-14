package com.travelbackend.services;

import com.travelbackend.entity.Payment;

import java.util.List;

public interface PaymentService {
    void savePayment(Payment payment, int travelId);
    List<Payment> findAll();
    Payment findById (int id);
    void updatePayment(Payment payment,int paymentId, int travelId);

    void deletePayment(int id);
}
