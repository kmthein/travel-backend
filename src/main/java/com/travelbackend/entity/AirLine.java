package com.travelbackend.entity;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "airline")
public class AirLine extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @JsonManagedReference
    @OneToOne(mappedBy = "airline", cascade = {
            CascadeType.DETACH,  CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH
    })
    private Image image;


}
