package com.travelbackend.dao;

import com.travelbackend.entity.Payment;

import java.util.List;

public interface PaymentDAO {

    void save(Payment payment);

    Payment findPaymentById(int paymentId);

    List<Payment> findAll();

    void update(Payment payment);

    void delete(int paymentId);
}
