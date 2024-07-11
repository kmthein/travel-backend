package com.travelbackend.services;

import com.travelbackend.dao.DestinationDAO;
import com.travelbackend.dao.ImageDAO;
import com.travelbackend.dto.ResponseDTO;
import com.travelbackend.entity.Destination;
import com.travelbackend.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DestinationServiceImpl implements DestinationService {

    @Autowired
    private DestinationDAO destinationDAO;

    @Autowired
    private ImageDAO imageDAO;

    @Override
    public ResponseDTO addNewDestination(Destination destination, List<String> imgUrls) {
        List<Image> imagesList = new ArrayList<>();
        for (String img : imgUrls) {
            Image image = new Image();
            image.setImgUrl(img);
            image.setDestination(destination);
            imagesList.add(image);
        }
        destination.setImage(imagesList);
        destinationDAO.save(destination);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("New Destination Created Successfully");
        return responseDTO;
    }

    @Override
    public ResponseDTO updateDestination(Destination destination, int id, List<String> imgUrls, List<Integer> deleteImgIds) {
        if (deleteImgIds != null) {
            for (int deleteId : deleteImgIds) {
                Image tempImg = imageDAO.findImageById(deleteId);
                if (tempImg != null) {
                    tempImg.setDelete(true);
                    imageDAO.update(tempImg);
                }
            }
        }
        if (imgUrls != null) {
            List<Image> imagesList = new ArrayList<>();
            for (String img : imgUrls) {
                Image image = new Image();
                image.setImgUrl(img);
                image.setDestination(destination);
                imagesList.add(image);
            }
            destination.setImage(imagesList);
        }
        destinationDAO.update(destination);
        return null;
    }
}
