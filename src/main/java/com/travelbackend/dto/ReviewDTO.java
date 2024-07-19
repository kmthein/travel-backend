package com.travelbackend.dto;

import com.travelbackend.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private int id;
    private String description;
    private int rating;
    private int userId;
    private String username;
    private List<String> userImg;
    private LocalDateTime createdAt;
}
