package me.minho.reservation.domain.response;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
public class RestResponse<T> {

    private final ResultCode resultCode;
    private final T data;

    public RestResponse(ResultCode resultCode, T data) {
        this.resultCode = resultCode;
        this.data = data;
    }

    public ResponseEntity<RestResponse<T>> toResponseEntity(HttpStatus status) {
        return ResponseEntity.status(status).body(this);
    }
}
