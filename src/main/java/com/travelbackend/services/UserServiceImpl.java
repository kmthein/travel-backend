package com.travelbackend.services;

import com.travelbackend.dao.ImageDAO;
import com.travelbackend.dao.TravelPlanDAO;
import com.travelbackend.dao.UserDAO;
import com.travelbackend.dto.*;
import com.travelbackend.entity.*;
import com.travelbackend.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ImageDAO imageDAO;

    @Autowired
    private TravelPlanDAO travelPlanDAO;

    @Override
    public List<User> getAllUsers() {
        List<User> userList = userDAO.findAll();
        for (User u : userList) {
            List<Image> filteredImages = ImageUtils.filterNonDeleteImages(u.getImageList());
            u.setImageList(filteredImages);
        }
        return userList;
    }

    @Override
    public ResponseDTO addNewUser(User user, List<String> imgUrls) {
        List<Image> imagesList = new ArrayList<>();
        if (imgUrls != null) {
            for (String img : imgUrls) {
                Image image = new Image();
                image.setImgUrl(img);
                image.setUser(user);
                imagesList.add(image);
            }
            user.setImageList(imagesList);
        }
        userDAO.save(user);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("New user created");
        return responseDTO;
    }

    @Override
    public User getUserById(int id) {
        User user = userDAO.findUserById(id);
        List<Image> filteredImages = ImageUtils.filterNonDeleteImages(user.getImageList());
        user.setImageList(filteredImages);
        return user;
    }

    @Override
    public ResponseDTO updateUser(User user, int id, List<String> imgUrls, List<Integer> deleteImg) {
        User tempUser = userDAO.findUserById(id);
        if (deleteImg != null) {
            for (int deleteId : deleteImg) {
                Image tempImg = imageDAO.findImageById(deleteId);
                if (tempImg != null) {
                    tempImg.setDelete(true);
                    imageDAO.update(tempImg);
                }
            }
        }
        if (imgUrls != null) {
            List<Image> imageList = new ArrayList<>();
            for (String img : imgUrls) {
                Image image = new Image();
                image.setImgUrl(img);
                image.setUser(user);
                imageList.add(image);
            }
            tempUser.setImageList(imageList);
        }
        tempUser.setUsername(user.getUsername());
        tempUser.setEmail(user.getEmail());
        tempUser.setPassword(user.getPassword());
        tempUser.setContactNumber(user.getContactNumber());
        tempUser.setRole(user.getRole());
        tempUser.setDob(user.getDob());
        tempUser.setEmailReceive(user.isEmailReceive());
        tempUser.setAddress(user.getAddress());
        userDAO.update(tempUser);
        return null;
    }

    @Override
    public ResponseDTO deleteById(int id) {
        User user = userDAO.findUserById(id);
        if (user != null) {
            user.setDelete(true);
        }
        List<Image> images = imageDAO.findImageByTypeId("user", id);
        for (Image i : images) {
            i.setDelete(true);
            imageDAO.update(i);
        }
        userDAO.update(user);
        return new ResponseDTO("User Deleted");
    }

    @Override
    public List<UserDTO> getAllNormalUsers() {
        List<User> users = userDAO.findByNormalUser();
        List<UserDTO> usersList = new ArrayList();
        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setEmail(user.getEmail());
            userDTO.setContactNumber(user.getContactNumber());
            userDTO.setDob(user.getDob());
            userDTO.setImage(user.getImageList());
            userDTO.setRole(user.getRole());
            userDTO.setEmailReceived(user.isEmailReceive());
            usersList.add(userDTO);
        }
        return usersList;
    }

    @Override
    public UserDTO editUser(UserDTO userDTO) {
        User user = userDAO.findUserById(userDTO.getId());

        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setContactNumber(userDTO.getContactNumber());
        user.setAddress(userDTO.getAddress());

        // Retrieve the current images of the user
        List<Image> currentImageList = new ArrayList<>();
        for (Image img : user.getImageList()) {
            if (!img.isDelete()) {
                currentImageList.add(img);
            }
        }

        if (userDTO.getImage() != null && !userDTO.getImage().isEmpty()) {
            // Process new images
            List<Image> newImages = new ArrayList<>();
            for (Object img : userDTO.getImage()) {
                if (img instanceof String) { // Ensure the img is a string before casting
                    Image image = new Image();
                    image.setUser(user);
                    image.setImgUrl((String) img);
                    newImages.add(image);
                } else {
                    throw new IllegalArgumentException("Invalid image URL: " + img);
                }
            }
            user.setImageList(newImages); // Update the user's image list with new images
            userDTO.setImage(newImages);

            // Mark existing images for deletion if they are not in the new list
            Set<String> newImageUrls = (Set<String>) new HashSet<>(userDTO.getImage());
            for (Image img : currentImageList) {
                if (!newImageUrls.contains(img.getImgUrl())) {
                    img.setDelete(true);
                    img.setUser(user);
                    imageDAO.update(img);
                }
            }
        } else {
            // If no new images, keep the existing images as they are
            user.setImageList(currentImageList);
            userDTO.setImage(currentImageList);
        }

        // Update the user entity
        userDAO.update(user);
        return userDTO;
    }

    @Override
    public List<TravelPlanDTO> getTravelPlanByUserId(int userId) {

        List<TravelPlanDTO> travelPlanDTOList = new ArrayList<>();
        List<TravelPlan> travelPlanList = travelPlanDAO.findAll();


        for(TravelPlan t : travelPlanList) {
            if(t.getUser().getId() == userId){
                TravelPlanDTO travelPlanDTO = new TravelPlanDTO();
                travelPlanDTO.setId(t.getId());
                travelPlanDTO.setStartDate(t.getStartDate());
                travelPlanDTO.setEndDate(t.getEndDate());
                travelPlanDTO.setTotalPrice(t.getTotalPrice());
                travelPlanDTO.setStatus(t.getStatus());

                if(t.getBusClass() != null && t.getBusSchedule() != null ) {
                    BusClassDTO busClassDTO = new BusClassDTO();
                    BusClass busClass = t.getBusClass();

                    busClassDTO.setBusClassId(busClass.getId());
                    busClassDTO.setBusClassName(busClass.getName());
                    busClassDTO.setBusServiceId(busClass.getBusService().getId());
                    List<String> imgUrlList = busClass.getBusService().getImage().stream().map(Image::getImgUrl).toList();
                    busClassDTO.setBusServiceImgUrl(imgUrlList);
                    busClassDTO.setBusServiceName(busClass.getBusService().getName());
                    busClassDTO.setPrice(busClass.getPrice());

                    travelPlanDTO.setBusClassDTO(busClassDTO);

                    BusScheduleDTO busScheduleDTO = new BusScheduleDTO();
                    BusSchedule busSchedule = t.getBusSchedule();

                    busScheduleDTO.setDeparturePlaceName(busSchedule.getDeparturePlace().getName());
                    busScheduleDTO.setArrivalPlaceName(busSchedule.getArrivalPlace().getName());
                    busScheduleDTO.setDistance(busSchedule.getDistance());
                    busScheduleDTO.setDepartureDate(busSchedule.getDate());
                    busScheduleDTO.setDepartureTime(busSchedule.getDepartureTime());
                    busScheduleDTO.setArrivalTime(busSchedule.getArrivalTime());

                    travelPlanDTO.setBusScheduleDTO(busScheduleDTO);

                }
                if(t.getFlightClass() != null && t.getFlightSchedule() != null ) {
                    FlightClassDTO flightClassDTO = new FlightClassDTO();
                    FlightClass flightClass = t.getFlightClass();

                    flightClassDTO.setFlightClassId(flightClass.getId());
                    flightClassDTO.setFlightClassName(flightClass.getName());
                    flightClassDTO.setAirlineId((flightClass.getAirline().getId()));
                    List<String> imgUrlList = flightClass.getAirline().getImage().stream().map(Image::getImgUrl).toList();
                    flightClassDTO.setAirlineImgUrl(imgUrlList);
                    flightClassDTO.setAirlineName(flightClass.getAirline().getName());
                    flightClassDTO.setPrice(flightClass.getPrice());

                    travelPlanDTO.setFlightClassDTO(flightClassDTO);

                    FlightScheduleDTO flightScheduleDTO = new FlightScheduleDTO();
                    FlightSchedule flightSchedule = t.getFlightSchedule();

                    flightScheduleDTO.setDeparturePlaceName(flightSchedule.getDeparturePlace().getName());
                    flightScheduleDTO.setArrivalPlaceName(flightSchedule.getArrivalPlace().getName());
                    flightScheduleDTO.setDistance(flightSchedule.getDistance());
                    flightScheduleDTO.setDepartureDate(flightSchedule.getDate());
                    flightScheduleDTO.setDepartureTime(flightSchedule.getDepartureTime());
                    flightScheduleDTO.setArrivalTime(flightSchedule.getArrivalTime());

                    travelPlanDTO.setFlightScheduleDTO(flightScheduleDTO);

                }

                if((t.getAccommodation() != null)){
                    AccommodationDTO accommodationDTO = new AccommodationDTO();
                    Accommodation accommodation = t.getAccommodation();

                    accommodationDTO.setCheckInDate(accommodation.getCheckIn());
                    accommodationDTO.setCheckOutDate(accommodation.getCheckOut());
                    accommodationDTO.setTotalPrice(accommodation.getTotalPrice());
                    accommodationDTO.setRoomName(accommodation.getRoom().getRoomType());
                    accommodationDTO.setHotelName(accommodation.getRoom().getHotel().getName());
                    accommodationDTO.setHotelImgUrl(accommodation.getRoom().getHotel().getImage().getFirst().getImgUrl());

                    travelPlanDTO.setAccommodationDTO(accommodationDTO);
                }


                travelPlanDTOList.add(travelPlanDTO);
            }
        }

        return travelPlanDTOList;
    }
}
