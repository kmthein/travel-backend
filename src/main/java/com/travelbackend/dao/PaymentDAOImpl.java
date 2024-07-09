package com.travelbackend.dao;

import com.travelbackend.entity.Payment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PaymentDAOImpl implements  PaymentDAO{

    private EntityManager entityManager;

    @Autowired
    public PaymentDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Payment payment) {
        entityManager.persist(payment);
    }

    @Override
    public Payment findPaymentById(int paymentId) {
        return entityManager.find(Payment.class,paymentId);
    }

    @Override
    public List<Payment> findAll() {
        TypedQuery<Payment> query = entityManager.createQuery("from Payment ",Payment.class);
            return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Payment payment) {
        entityManager.merge(payment);
    }

    @Override
    @Transactional
    public void delete(int paymentId) {
        Payment payment = entityManager.find(Payment.class,paymentId);
        entityManager.remove(payment);
    }
}
