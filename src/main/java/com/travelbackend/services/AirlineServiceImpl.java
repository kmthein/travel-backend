package com.travelbackend.services;

import com.travelbackend.dao.AirLineDAO;
import com.travelbackend.dao.ImageDAO;
import com.travelbackend.dto.TransportDTO;
import com.travelbackend.entity.AirLine;
import com.travelbackend.entity.Image;
import com.travelbackend.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirlineServiceImpl implements AirlineService {
    @Autowired
    private AirLineDAO airLineDAO;
    @Autowired
    private ImageDAO imageDAO;

    @Override
    public void save(TransportDTO transportDTO) {
        AirLine airLine = new AirLine();
        Image image = new Image();
        List<Image> imgList = new ArrayList<>();
        airLine.setName(transportDTO.getName());
        image.setAirline(airLine);
        image.setImgUrl(transportDTO.getImgUrl());
        imgList.add(image);
        airLine.setImage(imgList);
        airLineDAO.save(airLine);
    }

    @Override
    public List<AirLine> findAll() {
        List<AirLine> airLines = airLineDAO.findAll();
        for(AirLine airLine : airLines){
            List<Image> filterImg = ImageUtils.filterNonDeleteImages(airLine.getImage());
            airLine.setImage(filterImg);
        }
        return airLines;
    }

    @Override
    public void update(int id, TransportDTO transportDTO) {
        AirLine currentAirLine = airLineDAO.findAirLineById(id);

        if (currentAirLine != null) {
            currentAirLine.setName(transportDTO.getName());
            Image currentImage = imageDAO.findbyAirlineid(currentAirLine.getId());
            if (currentImage != null && !currentImage.getImgUrl().equals(transportDTO.getImgUrl())) {
                List<Image> imgList = new ArrayList<>();
                currentImage.setDelete(true);
                imageDAO.update(currentImage);
                Image image = new Image();
                image.setImgUrl(transportDTO.getImgUrl());
                image.setAirline(currentAirLine);
                imgList.add(image);
                currentAirLine.setImage(imgList);
            }
            airLineDAO.update(currentAirLine);
        }

    }

    @Override
    public AirLine getById(int id) {

        AirLine airLine =  airLineDAO.findAirLineById(id);
        List<Image> filterImg = ImageUtils.filterNonDeleteImages(airLine.getImage());
        airLine.setImage(filterImg);
        return airLine;
    }

    @Override
    public void deleteAirline(int id) {
        AirLine currentAirLine = airLineDAO.findAirLineById(id);
        Image currentImage = imageDAO.delete(id);
        currentAirLine.setDelete(true);
        currentImage.setDelete(true);
        airLineDAO.update(currentAirLine);
        imageDAO.update(currentImage);

    }
}
