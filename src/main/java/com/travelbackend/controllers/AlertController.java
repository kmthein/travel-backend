package com.travelbackend.controllers;


import com.travelbackend.entity.Alert;
import com.travelbackend.services.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class AlertController {
    private  AlertService alertService;

    @Autowired
    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping("/alert")
    public ResponseEntity<?> createAlert(@RequestBody Alert alert, @RequestParam int userId) {
        alertService.saveAlert(alert, userId);
        return ResponseEntity.ok("Create alert successfully");
    }

    @GetMapping("/alert")
    public List<Alert> findAllAlert() {
        return alertService.findAllAlert();
    }
    @GetMapping("/alert/{id}")
    public Alert findAlertById(@PathVariable int id) {
        return alertService.findAlertById(id);
    }

    @PutMapping("/alert/{id}")
    public ResponseEntity<?> updateAlert(@ModelAttribute Alert alert, @PathVariable int AlertId,@RequestParam int userId) {
        alertService.updateAlert(alert, AlertId, userId);
        return ResponseEntity.ok("Update alert successfully");
    }

    @PostMapping("/alert/{id}")
    public String deleteAlert(@PathVariable int id) {
        alertService.deleteAlert(id);
        return "Delete alert successfully";
    }
}
