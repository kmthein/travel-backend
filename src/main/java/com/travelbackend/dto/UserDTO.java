package com.travelbackend.dto;

import com.travelbackend.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class UserDTO {
    private int id;
    private String username;
    private String email;
    private String contactNumber;
    private LocalDate dob;
    private String address;
    private Role role;
    private List<?> image;
    private Boolean emailReceived;
}
