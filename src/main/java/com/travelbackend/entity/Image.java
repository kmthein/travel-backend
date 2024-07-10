package com.travelbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image")
public class Image extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "img_url")
    private String imgUrl;

    @OneToOne(cascade = {
            CascadeType.DETACH,  CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH
    })
    @JoinColumn(name = "user_id")
    private User user;

    @JsonBackReference
    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "airline_id")
    private AirLine airline;

    @OneToOne(cascade = {
            CascadeType.DETACH,  CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH
    })
    @JoinColumn(name = "bus_id")
    private BusService busService;

    @ManyToOne(cascade = {
            CascadeType.DETACH,  CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH
    })
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne(cascade = {
            CascadeType.DETACH,  CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH
    })
    @JoinColumn(name = "destination_id")
    private Destination destination;
}
