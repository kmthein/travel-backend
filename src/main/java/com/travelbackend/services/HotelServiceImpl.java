package com.travelbackend.services;

import com.travelbackend.dao.AccommodationDAO;
import com.travelbackend.dao.DestinationDAO;
import com.travelbackend.dao.HotelDAO;
import com.travelbackend.dao.ImageDAO;
import com.travelbackend.dto.*;
import com.travelbackend.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    private final DestinationDAO destinationDAO;

    private final HotelDAO hotelDAO;

    private final ImageDAO imageDAO;

    private final AccommodationDAO accommodationDAO;

    private final RoomService roomService;

    public HotelServiceImpl(DestinationDAO destinationDAO, HotelDAO hotelDAO, ImageDAO imageDAO, AccommodationDAO accommodationDAO, RoomService roomService) {
        this.destinationDAO = destinationDAO;
        this.hotelDAO = hotelDAO;
        this.imageDAO = imageDAO;
        this.accommodationDAO = accommodationDAO;
        this.roomService = roomService;
    }

    @Override
    @Transactional
    public void save(HotelDTO hotelDTO) throws Exception {
        Hotel hotel = new Hotel();

        //Name
        String name = hotelDTO.getName();
        if (name == null || name.isEmpty()) {
            throw new Exception("Name is required");
        }
        hotel.setName(name);

        //Description
        hotel.setDescription(hotelDTO.getDescription());

        //Rating
        Double rating = hotelDTO.getRating();
        if(rating == null) {
            throw new Exception("Rating is required");
        }
        hotel.setRating(hotelDTO.getRating());

        //Destination
        Integer destinationId = hotelDTO.getDestinationId();
        if (destinationId == null) {
            throw new Exception("Destination is required");
        }
        Destination destination = destinationDAO.findDestinationById(hotelDTO.getDestinationId());
        hotel.setDestination(destination);

        //Image
        List<String> imgUrlList = hotelDTO.getImgUrlList();
        if(!imgUrlList.isEmpty()){
            ArrayList<Image> imageArray = new ArrayList<>();
            for (String s : imgUrlList) {
                Image image = new Image();
                image.setHotel(hotel);
                image.setImgUrl(s);

                imageDAO.save(image);

                imageArray.add(image);
            }
            hotel.setImage(imageArray);
        }

        //Save
        hotelDAO.save(hotel);

    }

    @Override
    public List<HotelListDTO> getHotelsFromUser() {
        List<Hotel> hotelList = hotelDAO.findAllJoin()
                .stream()
                .filter(h -> !h.isDelete())
                .toList();
        List<HotelListDTO> hotelListDTOList = new ArrayList<>();
        for (Hotel hotel : hotelList) {
            HotelListDTO hotelListDTO = new HotelListDTO();
            hotelListDTO.setId(hotel.getId());
            hotelListDTO.setName(hotel.getName());
            hotelListDTO.setDescription(hotel.getDescription());
            hotelListDTO.setRating(hotel.getRating());
            hotelListDTO.setDestination(hotel.getDestination());
            hotelListDTO.setRoomList(hotel.getRoomList());
            List<String> imgUrlList = new ArrayList<>();
            for (Image img : hotel.getImage()) {
                imgUrlList.add(img.getImgUrl());
            }
            hotelListDTO.setImgUrlList(imgUrlList);
            hotelListDTOList.add(hotelListDTO);
        }
        return hotelListDTOList;
    }

    @Override
    public List<HotelDTO> getAll() {
        List<Hotel> hotelList = hotelDAO.findAll()
                .stream()
                .filter(h -> !h.isDelete())
                .toList();

        for(Hotel h: hotelList) {
            h.setImage(h.getImage().stream().filter(i -> !i.isDelete()).toList());
        }

        List<HotelDTO> hotelDTOList = new ArrayList<>();

        for(Hotel h :hotelList){
            HotelDTO hotelDTO = getHotelDTO(h);

            hotelDTOList.add(hotelDTO);
        }
        return hotelDTOList;
    }


    @Override
    public HotelDTO get(int id) throws Exception {

        Hotel hotel = hotelDAO.findHotelById(id);
        if(hotel == null || hotel.isDelete()){
            throw new Exception("Hotel not found");
        }

        hotel.setImage(hotel.getImage().stream().filter(i -> !i.isDelete()).toList());

        return getHotelDTO(hotel);
    }

    @Override
    @Transactional
    public void delete(int id) throws Exception {
        try{
            Hotel hotel = hotelDAO.findHotelById(id);
            List<Image> imageList = hotel.getImage();
            if (imageList != null) {
                for (Image img: imageList) {
                    img.setDelete(true);
                }
            }
            hotel.setDelete(true);
        } catch(Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void update(HotelDTO hotelDTO, int id) throws Exception {
        Hotel hotel = hotelDAO.findHotelById(id);

        //Name
        String name = hotelDTO.getName();
        if (name == null || name.isEmpty()) {
            throw new Exception("Name is required");
        }
        hotel.setName(name);

        //Description
        hotel.setDescription(hotelDTO.getDescription());

        //Rating
        Double rating = hotelDTO.getRating();
        if(rating == null) {
            throw new Exception("Rating is required");
        }
        hotel.setRating(hotelDTO.getRating());

        //Destination
        Integer destinationId = hotelDTO.getDestinationId();
        if (destinationId == null) {
            throw new Exception("Destination is required");
        }
        Destination destination = destinationDAO.findDestinationById(hotelDTO.getDestinationId());
        hotel.setDestination(destination);

        //Image
        List<String> imgUrlList = hotelDTO.getImgUrlList();

        List<Image> oldImgList = hotel.getImage();


        if(!imgUrlList.isEmpty()){

            List<String> oldImgUrlList = new ArrayList<>();
            for (Image img:oldImgList) {
                oldImgUrlList.add(img.getImgUrl());
                if(!imgUrlList.contains(img.getImgUrl())){
                    img.setDelete(true);
                } else {
                    img.setDelete(false);
                }
            }

            for (String s: imgUrlList) {
                if(!oldImgUrlList.contains(s)) {
                    Image image = new Image();
                    image.setHotel(hotel);
                    image.setImgUrl(s);

                    imageDAO.save(image);

                    oldImgList.add(image);
                }
            }
            hotel.setImage(oldImgList);
        }

        //Save
        hotelDAO.save(hotel);

    }

    @Override
    public List<HotelDTO> getAllAvailableHotels(FindHotelDTO findHotelDTO) {
        String searchString = findHotelDTO.getSearchString();
        LocalDateTime checkInDate = findHotelDTO.getCheckInDate();
        LocalDateTime checkOutDate = findHotelDTO.getCheckOutDate();
        int numberOfPerson = findHotelDTO.getNumberOfPerson();

        List<Hotel> hotelList = hotelDAO.findAll();
        List<Accommodation> accommodationList = accommodationDAO.findAll();

        List<HotelDTO> hotelDTOList = new ArrayList<>();

        for(Hotel h : hotelList) {
            HotelDTO hotelDTO = getHotelDTO(h);
            int totalAvailableRoom = 0;

            List<Room> roomList = h.getRoomList();
            List<RoomDTO> roomDTOList = new ArrayList<>();

            for(Room r : roomList) {
                FindRoomDTO findRoomDTO = new FindRoomDTO();
                findRoomDTO.setId(r.getId());
                findRoomDTO.setCheckInDate(checkInDate);
                findRoomDTO.setCheckOutDate(checkOutDate);

                if(!accommodationList.isEmpty()) {
                    for(Accommodation a : accommodationList) {
                        if(r.getId() == a.getRoom().getId()) {
                            RoomDTO roomDTO = roomService.getAvailableRoom(findRoomDTO);
                            if (roomDTO.getAvailableRoom() > 0) {
                                roomDTOList.add(roomDTO);
                                totalAvailableRoom += roomDTO.getAvailableRoom();
                            }
                        } else {
                            RoomDTO roomDTO = roomService.getAvailableRoom(findRoomDTO);
                            roomDTOList.add(roomDTO);
                            totalAvailableRoom += roomDTO.getAvailableRoom();
                        }
                    }
                } else {
                    RoomDTO roomDTO = roomService.getAvailableRoom(findRoomDTO);
                    roomDTOList.add(roomDTO);
                    totalAvailableRoom += roomDTO.getAvailableRoom();
                }
            }
            hotelDTO.setAvailableRoomList(roomDTOList);

            if(totalAvailableRoom > 0) {
                hotelDTO.setHasRoom(true);
            } else {
                hotelDTO.setHasRoom(false);
            }
            hotelDTOList.add(hotelDTO);
        }
        List<HotelDTO> filteredList = hotelDTOList.stream().filter(HotelDTO::isHasRoom).toList();

        if(searchString != null){
            filteredList = filteredList
                    .stream()
                    .filter(h -> h.getName().toLowerCase().contains(searchString.toLowerCase()) || destinationDAO.findDestinationById(h.getDestinationId()).getName().toLowerCase().contains(searchString.toLowerCase()))
                    .toList();
        }
        return filteredList;
    }

    private HotelDTO getHotelDTO(Hotel h) {
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setId(h.getId());
        hotelDTO.setName(h.getName());
        hotelDTO.setDescription(h.getDescription());
        hotelDTO.setRating(h.getRating());
        hotelDTO.setDestinationId(h.getDestination().getId());
        hotelDTO.setLocation(destinationDAO.findDestinationById(h.getDestination().getId()).getName());

        List<Image> imageList = h.getImage();
        List<String> imageUrlList = new ArrayList<>();
        for(Image img: imageList) {
            imageUrlList.add(img.getImgUrl());
        }
        hotelDTO.setImgUrlList(imageUrlList);
        return hotelDTO;
    }
}
