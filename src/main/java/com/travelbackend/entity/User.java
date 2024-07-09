package com.travelbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "role")
    private String role;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "email_received")
    private boolean emailReceive;

    @Column(name = "address")
    private String address;

}
