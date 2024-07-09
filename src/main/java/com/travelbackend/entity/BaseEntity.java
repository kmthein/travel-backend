package com.travelbackend.entity;

import jakarta.persistence.Column;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class BaseEntity {
    @Column(name = "is_delete")
    private boolean isDelete;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updatedAt;
}
