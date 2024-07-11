package com.travelbackend.services;

import com.travelbackend.dao.AirLineDAO;
import com.travelbackend.dao.ImageDAO;
import com.travelbackend.dto.AirlineDTO;
import com.travelbackend.entity.AirLine;
import com.travelbackend.entity.Image;
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
    public void save(AirlineDTO airlineDTO) {
        AirLine airLine = new AirLine();
        Image image = new Image();
        List<Image> imgList = new ArrayList<>();
        airLine.setName(airlineDTO.getName());
        image.setAirline(airLine);
        image.setImgUrl(airlineDTO.getImgUrl());
        imgList.add(image);
        airLine.setImage(imgList);
        airLineDAO.save(airLine);
    }

    @Override
    public List<AirLine> findAll() {
        return airLineDAO.findAll();
    }

    @Override
    public void update(AirlineDTO airlineDTO,int id) {


    }

    @Override
    public AirLine getById(int id) {
        return airLineDAO.findAirLineById(id);
    }
}
