package com.acelera.ti.stock.infrastructure.response;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ResponseDTO {

    private Timestamp timestamp;
    private Object data;
    private int respondeCode;
    private String status;
    private String description;

    public ResponseDTO(Object data, int respondeCode, String status) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.data = data;
        this.respondeCode = respondeCode;
        this.status = status;
    }

    public ResponseDTO(Object data, int respondeCode, String status, String description) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.data = data;
        this.respondeCode = respondeCode;
        this.status = status;
        this.description = description;
    }

}

