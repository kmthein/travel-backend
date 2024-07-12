package com.travelbackend.services;

import com.travelbackend.dao.ImageDAO;
import com.travelbackend.dao.UserDAO;
import com.travelbackend.dao.UserDAOImpl;
import com.travelbackend.dto.ResponseDTO;
import com.travelbackend.entity.Image;
import com.travelbackend.entity.User;
import com.travelbackend.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ImageDAO imageDAO;

    @Override
    public List<User> getAllUsers() {
        List<User> userList = userDAO.findAll();
        for(User u: userList){
            List<Image>filteredImages = ImageUtils.filterNonDeleteImages(u.getImageList());
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
        if(deleteImg != null){
            for(int deleteId : deleteImg){
                Image tempImg = imageDAO.findImageById(deleteId);
                if(tempImg != null){
                    tempImg.setDelete(true);
                    imageDAO.update(tempImg);
                }
            }
        }
        if(imgUrls != null){
            List<Image> imageList = new ArrayList<>();
            for(String img: imgUrls){
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
        if(user != null) {
            user.setDelete(true);
        }
        List<Image> images = imageDAO.findImageByTypeId("user",id);
        for(Image i : images){
            i.setDelete(true);
            imageDAO.update(i);
        }
        userDAO.update(user);
        return new ResponseDTO("User Deleted");
    }
}
