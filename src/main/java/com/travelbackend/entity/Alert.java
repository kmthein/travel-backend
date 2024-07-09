package com.travelbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alert")
public class Alert extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "alert_type")
    private String alertType;

    @Column(name = "alert_message")
    private String alertMessage;

    @Column(name = "alert_time")
    private LocalDateTime alertTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
