package com.vaka.daily_client.model;

import lombok.Data;

@Data
public class ResponseError {
    private String error;
    private String message;
    private Integer status;
}
