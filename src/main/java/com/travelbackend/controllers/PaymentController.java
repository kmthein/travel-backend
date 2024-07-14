package com.travelbackend.controllers;

import com.travelbackend.entity.Payment;
import com.travelbackend.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment")
    public ResponseEntity<?> createPayment(@ModelAttribute Payment payment, @RequestParam int travelId){
        paymentService.savePayment(payment,travelId);
        return ResponseEntity.ok("Payment Created!");
    }

    @GetMapping("/payment")
    public List<Payment> findAllPayment(){
        return paymentService.findAll();
    }

    @GetMapping("/payment/{id}")
    public Payment findById(@PathVariable int id){
        return paymentService.findById(id);
    }

    @PutMapping("/payment/{id}")
    public ResponseEntity<?> updatePayment(@ModelAttribute Payment payment,@PathVariable int id,@RequestParam int travelId){
        paymentService.updatePayment(payment,id,travelId);
        return ResponseEntity.ok("Update Successful");
    }

    @DeleteMapping("/payment/{id}")
   public ResponseEntity<?> deletePayment(@PathVariable int id){
        paymentService.deletePayment(id);
        return ResponseEntity.ok("Delete Successful");
    }
}
