package com.travelbackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;
import java.time.LocalTime;

@MappedSuperclass
public class BaseEntity {
    @Column(name = "is_delete")
    private boolean isDelete;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
