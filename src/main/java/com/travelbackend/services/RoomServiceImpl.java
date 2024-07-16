package com.travelbackend.services;

import com.travelbackend.dao.HotelDAO;
import com.travelbackend.dao.ImageDAO;
import com.travelbackend.dao.RoomDAO;
import com.travelbackend.dto.ResponseDTO;
import com.travelbackend.dto.RoomDTO;
import com.travelbackend.entity.Hotel;
import com.travelbackend.entity.Image;
import com.travelbackend.entity.Room;
import com.travelbackend.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class RoomServiceImpl implements RoomService{
    private HotelDAO hotelDAO;
    private ImageDAO imageDAO;
    private RoomDAO roomDAO;
    @Autowired
    public RoomServiceImpl(RoomDAO roomDAO, HotelDAO hotelDAO, ImageDAO imageDAO){
        this.roomDAO = roomDAO;
        this.hotelDAO = hotelDAO;
        this.imageDAO = imageDAO;
    }


    @Override
    @Transactional
    public ResponseDTO addNewRoom(RoomDTO roomDTO) throws Exception {

        Room room = new Room();

        //Room Type
        String roomType = roomDTO.getRoomType();
        if(roomType == null) {
            throw new Exception("Room Type is required");
        }
        room.setRoomType(roomType);

        //Valid Room
        Integer validRoom = roomDTO.getValidRoom();
        if(validRoom == null) {
            throw new Exception("Valid Room is required");
        }
        room.setValidRoom(validRoom);

        //Room Price
        Integer roomPrice = roomDTO.getRoomPrice();
        if(roomPrice == null) {
            throw new Exception("Room Price is required");
        }
        room.setRoomPrice(roomPrice);

        //Hotel
        Integer hotelId = roomDTO.getHotelId();
        if(hotelId == null) {
            throw new Exception("Hotel ID is required");
        }
        Hotel h = hotelDAO.findHotelById(roomDTO.getHotelId());
        room.setHotel(h);

        //Image
        List<String> imgUrlList = roomDTO.getImgUrlList();
        if(!imgUrlList.isEmpty()){
            ArrayList<Image> imageArray = new ArrayList<>();
            for (String s : imgUrlList) {
                Image image = new Image();
                image.setRoom(room);
                image.setImgUrl(s);

                imageDAO.save(image);

                imageArray.add(image);
            }
            room.setImage(imageArray);
        }

        roomDAO.save(room);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("New Room Created Successfully");
        return responseDTO;
    }

    @Override
    public List<Room> getAllRooms() {
        List<Room> roomList = roomDAO.findAll();
        for (Room r: roomList){
            List<Image>filteredImage = ImageUtils.filterNonDeleteImages(r.getImage());
            r.setImage(filteredImage);
        }
        return roomList;
    }

    @Override
    public Room getRoombyId(int id) {
        Room room = roomDAO.findRoomById(id);
        List<Image> filereImages = ImageUtils.filterNonDeleteImages(room.getImage());
        room.setImage(filereImages);
        return room;
    }

    @Override
    public ResponseDTO updateRoom(Room room, int roomId, int hotelId, List<String> imgUrls, List<Integer> deleteImg) {
        Room tempRoom = roomDAO.findRoomById(roomId);
        Hotel hotel = hotelDAO.findHotelById(hotelId);
        if (deleteImg != null){
            for (int deleteId : deleteImg){
                Image temImg = imageDAO.findImageById(deleteId);
                if (temImg != null){
                    temImg.setDelete(true);
                    imageDAO.update(temImg);
                }
            }
        }
        if (imgUrls != null){
            List<Image> imageList = new ArrayList<>();
            for (String img: imgUrls){
                Image image = new Image();
                image.setImgUrl(img);
                image.setRoom(tempRoom);
                imageList.add(image);
            }
            tempRoom.setImage(imageList);
        }
        tempRoom.setRoomType(room.getRoomType());
        tempRoom.setValidRoom(room.getValidRoom());
        tempRoom.setRoomPrice(room.getRoomPrice());
        tempRoom.setHotel(hotel);
        roomDAO.update(tempRoom);
        return null;
    }

    @Override
    public ResponseDTO deleteById(int id) {
        Room room = roomDAO.findRoomById(id);
        if (room != null){
            room.setDelete(true);
        }
        List<Image> images = imageDAO.findImageByTypeId("room",id);
        for (Image i : images){
            i.setDelete(true);
            imageDAO.update(i);
        }
        roomDAO.update(room);
        return new ResponseDTO("Room Deleted");
    }

}
