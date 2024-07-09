package com.travelbackend.dao;

import com.travelbackend.entity.Image;

import java.util.List;

public interface ImageDAO {

    void save(Image image);

    Image findImageById(int imageId);

    List<Image> findAll();

    void update(Image image);

    void delete(int imageId);
}
