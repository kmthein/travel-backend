package com.travelbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.cert.CertificateExpiredException;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment")
public class Payment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "status")
    private String status;

    @Column(name = "amount")
    private int amount;

    @Column(name = "notes")
    private String notes;

    @Column(name = "payment_img")
    private String paymentImg;

    @OneToOne
    @JoinColumn(name = "travel_id")
    private TravelPlan travelPlan;
}
