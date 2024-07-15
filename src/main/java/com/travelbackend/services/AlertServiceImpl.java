package com.travelbackend.services;

import com.travelbackend.dao.AlertDAO;
import com.travelbackend.dao.UserDAO;
import com.travelbackend.entity.Alert;
import com.travelbackend.entity.User;
import com.travelbackend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertServiceImpl implements AlertService{

    private AlertDAO alertDAO;

    private UserDAO userDAO;

    @Autowired
    public AlertServiceImpl(AlertDAO alertDAO, UserDAO userDAO) {
        this.alertDAO = alertDAO;
        this.userDAO = userDAO;
    }

    @Override
    public void saveAlert(Alert alert, int userId) {
        User u = userDAO.findUserById(userId);
        if (u == null) {
            throw  new ResourceNotFoundException("Alert not found");
        }
        alert.setUser(u);
        alertDAO.save(alert);
    }

    @Override
    public List<Alert> findAllAlert() {
        return alertDAO.findAll();
    }

    @Override
    public Alert findAlertById(int AlertId) {
        return alertDAO.findAlertById(AlertId);
    }

    @Override
    public void updateAlert(Alert alert, int AlertId, int userId) {
        Alert a = alertDAO.findAlertById(AlertId);
        if(a == null) {
            throw  new ResourceNotFoundException("Alert not found");
        }
        User u = userDAO.findUserById(userId);
        if(u == null) {
            throw  new ResourceNotFoundException("User not found");
        }
        a.setAlertMessage(alert.getAlertMessage());
        a.setAlertTime(alert.getAlertTime());
        a.setAlertType(alert.getAlertType());
        alertDAO.update(a);
    }

    @Override
    public void deleteAlert(int AlertId) {
        Alert a = alertDAO.findAlertById(AlertId);
        if(a == null) {
            throw  new ResourceNotFoundException("No data in table");
        }
    }
}
