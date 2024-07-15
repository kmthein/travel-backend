package com.travelbackend.services;

import com.travelbackend.entity.Alert;

import java.util.List;

public interface  AlertService {
    void saveAlert(Alert alert, int userId);

    List<Alert> findAllAlert();

    Alert findAlertById(int AlertId);

    void updateAlert(Alert alert,int AlertId, int userId);

    void deleteAlert(int AlertId);
}
