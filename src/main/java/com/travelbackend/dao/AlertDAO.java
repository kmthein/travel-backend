package com.travelbackend.dao;

import com.travelbackend.entity.Alert;

import java.util.List;

public interface  AlertDAO {

    void save(Alert alert);

    Alert findAlertById(int alertId);

    List<Alert> findAll();

    void update(Alert alert);

    void delete(int alertId);
}
