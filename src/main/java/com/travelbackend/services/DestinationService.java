package com.travelbackend.services;

import com.travelbackend.dto.ResponseDTO;
import com.travelbackend.entity.Destination;

import java.util.List;

public interface DestinationService {
    List<Destination> getAllDestinations();
    Destination getDestinationById(int id);
    ResponseDTO addNewDestination(Destination destination, List<String> imgUrls);
    ResponseDTO updateDestination(Destination destination, int id, List<String> imgUrls, List<Integer> deleteImgIds);
    List<Destination> searchDestinationByKeyword(String keyword);
    ResponseDTO getDestinationJoin();
    ResponseDTO deleteById(int id);
}
