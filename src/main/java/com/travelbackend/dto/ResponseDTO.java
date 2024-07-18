package com.travelbackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {
    private String message;
    private String status;
    private Integer id;

    public ResponseDTO(String message) {
        this.message = message;
    }

    public ResponseDTO(String message, String status) {
        this.message = message;
        this.status = status;
    }
}
