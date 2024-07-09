package com.travelbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room")
public class Room extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "valid_room")
    private int validRoom;

    @Column(name = "room_price")
    private int roomPrice;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
