package com.travelbackend.services;

import com.travelbackend.dao.PaymentDAO;
import com.travelbackend.dao.PaymentDAOImpl;
import com.travelbackend.dao.TravelPlanDAO;
import com.travelbackend.entity.Payment;
import com.travelbackend.entity.TravelPlan;
import com.travelbackend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{
    private PaymentDAO paymentDAO;
    private TravelPlanDAO travelPlanDAO;

    @Autowired
    public PaymentServiceImpl(PaymentDAO paymentDAO, TravelPlanDAO travelPlanDAO){
        this.paymentDAO=paymentDAO;
        this.travelPlanDAO=travelPlanDAO;
    }
    @Override
    public void savePayment(Payment payment, int travelId) {
        TravelPlan travel = travelPlanDAO.findTravelPlanById(travelId);
        if (travel == null){
            throw new ResourceNotFoundException("Payment not found");
        }
        payment.setTravelPlan(travel);
        paymentDAO.save(payment);
    }

    @Override
    public List<Payment> findAll() {
        return paymentDAO.findAll();
    }

    @Override
    public Payment findById(int id) {
        return paymentDAO.findPaymentById(id);
    }

    @Override
    public void updatePayment(Payment payment, int paymentId, int travelId) {
        Payment payment1 = paymentDAO.findPaymentById(paymentId);
        if (payment1 == null){
            throw new ResourceNotFoundException("Payment not found!");
        }
        TravelPlan travel1 = travelPlanDAO.findTravelPlanById(travelId);
        payment1.setPaymentDate(payment.getPaymentDate());
        payment1.setStatus(payment.getStatus());
        payment1.setAmount(payment.getAmount());
        payment1.setNotes(payment.getNotes());
        payment1.setPaymentImg(payment.getPaymentImg());
        payment1.setTravelPlan(travel1);
        paymentDAO.save(payment1);
    }

    @Override
    public void deletePayment(int id) {
        Payment p = paymentDAO.findPaymentById(id);
        if (p==null){
            throw new ResourceNotFoundException("Payment not found");
        }
        p.setDelete(true);
        paymentDAO.update(p);
    }
}
