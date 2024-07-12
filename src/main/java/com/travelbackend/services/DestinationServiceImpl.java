package com.travelbackend.services;

import com.travelbackend.dao.DestinationDAO;
import com.travelbackend.dao.ImageDAO;
import com.travelbackend.dto.ResponseDTO;
import com.travelbackend.entity.Destination;
import com.travelbackend.entity.Image;
import com.travelbackend.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DestinationServiceImpl implements DestinationService {

    @Autowired
    private DestinationDAO destinationDAO;

    @Autowired
    private ImageDAO imageDAO;

    @Override
    public List<Destination> getAllDestinations() {
        List<Destination> destinationList = destinationDAO.findAll();
        for (Destination d : destinationList) {
            List<Image> filteredImages = ImageUtils.filterNonDeleteImages(d.getImage());
            d.setImage(filteredImages);
        }
        return destinationList;
    }

    @Override
    public Destination getDestinationById(int id) {
        Destination destination = destinationDAO.findDestinationById(id);
        List<Image> filteredImages = ImageUtils.filterNonDeleteImages(destination.getImage());
        destination.setImage(filteredImages);
        return destination;
    }

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
    public ResponseDTO getDestinationJoin() {
        return null;
    }

    @Override
    public ResponseDTO updateDestination(Destination destination, int id, List<String> imgUrls, List<Integer> deleteImgIds) {
        Destination tempDest = destinationDAO.findDestinationById(id);
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
                image.setDestination(tempDest);
                imagesList.add(image);
            }
            tempDest.setImage(imagesList);
        }
        tempDest.setName(destination.getName());
        tempDest.setCountry(destination.getCountry());
        tempDest.setHighlight(destination.getHighlight());
        tempDest.setTopPlace(destination.getTopPlace());
        tempDest.setDescription(destination.getDescription());
        destinationDAO.update(tempDest);
        return null;
    }

    @Override
    public ResponseDTO deleteById(int id) {
        Destination destination = destinationDAO.findDestinationById(id);
        if (destination != null) {
            destination.setDelete(true);
        }
        List<Image> images = imageDAO.findImageByTypeId("destination", id);
        for (Image i : images) {
            i.setDelete(true);
            imageDAO.update(i);
        }
        destinationDAO.update(destination);
        return new ResponseDTO("Destination deleted");
    }
}
