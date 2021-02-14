package me.minho.reservation.reservation.web;

import lombok.RequiredArgsConstructor;
import me.minho.reservation.reservation.service.ReservationService;
import me.minho.reservation.reservation.web.dto.ReservationResponse;
import me.minho.reservation.reservation.web.dto.ReservationStatusUpdateRequest;
import me.minho.reservation.util.RestResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReservationApiController {

    private final ReservationService reservationService;

    @GetMapping("/reservations")
    public RestResponseData<List<ReservationResponse>> findAll() {
        return new RestResponseData(HttpStatus.OK, ReservationResponse.of(reservationService.findAll()));
    }

    @PutMapping("/reservation/{id}")
    public RestResponseData<Long> changeStatus(@PathVariable("id") long id, @RequestBody ReservationStatusUpdateRequest statusUpdateRequest) {
        try {
            return new RestResponseData(HttpStatus.OK, reservationService.changeStatus(id, statusUpdateRequest.getStatus()), "상태 변경 성공!");
        } catch (IllegalArgumentException e) {
            return new RestResponseData(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
