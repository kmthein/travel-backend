package com.travelbackend.services;

import com.travelbackend.dao.BusServiceDAO;
import com.travelbackend.dao.ImageDAO;
import com.travelbackend.dto.TransportDTO;
import com.travelbackend.entity.BusService;
import com.travelbackend.entity.Image;
import com.travelbackend.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusStationServiceImpl implements BusStationService {
    @Autowired
    private BusServiceDAO busServiceDAO;

    @Autowired
    private ImageDAO imageDAO;

    @Override
    public void save(TransportDTO transportDTO) {
        BusService busService = new BusService();
        Image image = new Image();
        List<Image> imgList = new ArrayList<>();
        busService.setName(transportDTO.getName());
        image.setImgUrl(transportDTO.getImgUrl());
        image.setBusService(busService);
        imgList.add(image);
        busService.setImage(imgList);
        busServiceDAO.save(busService);

    }

    @Override
    public List<BusService> findAll() {
        List<BusService> busServiceList = busServiceDAO.findAll();
        for(BusService busService : busServiceList){
            List<Image> filterImg = ImageUtils.filterNonDeleteImages(busService.getImage());
            busService.setImage(filterImg);
        }
        return busServiceList;
    }

    @Override
    public BusService getById(int id){
        BusService busService = busServiceDAO.findBusServiceById(id);
        List<Image> filterImg = ImageUtils.filterNonDeleteImages(busService.getImage());
        busService.setImage(filterImg);
        return busService;
    }

    @Override
    public void updateBusService(int id, TransportDTO transportDTO) {
        BusService currentBusService = busServiceDAO.findBusServiceById(id);
        if(currentBusService != null){
            currentBusService.setName(transportDTO.getName());
            Image currentImage = imageDAO.findByBusServiceId(currentBusService.getId());
            if(currentImage != null && !currentImage.getImgUrl().equals(transportDTO.getImgUrl())){
                List<Image> imageList = new ArrayList<>();
                currentImage.setDelete(true);
                imageDAO.update(currentImage);
                Image image = new Image();
                image.setImgUrl(transportDTO.getImgUrl());
                image.setBusService(currentBusService);
                imageList.add(image);
                currentBusService.setImage(imageList);
            }
            busServiceDAO.update(currentBusService);
        }
    }
}
