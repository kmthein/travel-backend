package com.travelbackend.services;

import com.travelbackend.dao.AirLineDAO;
import com.travelbackend.dao.ImageDAO;
import com.travelbackend.dto.AirlineDTO;
import com.travelbackend.entity.AirLine;
import com.travelbackend.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineServiceImpl implements AirlineService {
    @Autowired
    private AirLineDAO airLineDAO;
    @Autowired
    private ImageDAO imageDAO;

    @Override
    public void save(AirlineDTO airlineDTO) {
        AirLine airLine = new AirLine();
        Image image = new Image();
        airLine.setName(airlineDTO.getName());
        image.setAirline(airLine);
        image.setImgUrl(airlineDTO.getImgUrl());
        airLine.setImage(image);
        airLineDAO.save(airLine);
    }

    @Override
    public List<AirLine> findAll() {
        return airLineDAO.findAll();
    }

    @Override
    public void update(AirlineDTO airlineDTO,int id) {
        Image image = new Image();
        AirLine airLine = airLineDAO.findAirLineById(id);
        airLine.setName(airlineDTO.getName());
        Image img = imageDAO.findImageById(airLine.getImage().getId());
        if(img != null && !img.getImgUrl().equals(airlineDTO.getImgUrl())){
            img.setDelete(true);
            imageDAO.update(img);
            image.setImgUrl(airlineDTO.getImgUrl());
        }
        airLine.setImage(image);
        airLineDAO.update(airLine);
    }
}
