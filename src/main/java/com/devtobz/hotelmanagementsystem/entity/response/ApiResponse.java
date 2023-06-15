package com.devtobz.hotelmanagementsystem.entity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private ZonedDateTime timeStamp;
    private boolean isSuccessful;
    private Object data;
    private int status;

    private String message;
}
