package com.travelbackend.dao;

import com.travelbackend.entity.Image;

import java.util.List;

public interface ImageDAO {

    void save(Image image);

    public Image findImageById(int imageId);

    List<Image> findImageByTypeId(String type, int id);

    List<Image> findAll();

    void update(Image image);

    Image delete(int airlineId);

    Image findbyAirlineid(int airlineId);

    Image findByBusServiceId(int busServiceId);
}
