package me.minho.reservation.util;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public final class RestResponseData<T> {

    private HttpStatus httpStatus;

    private T data;

    private String message;

    public RestResponseData(HttpStatus httpStatus, T data) {
        this.httpStatus = httpStatus;
        this.data = data;
    }

    public RestResponseData(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public RestResponseData(HttpStatus httpStatus, T data, String message) {
        this.httpStatus = httpStatus;
        this.data = data;
        this.message = message;
    }
}