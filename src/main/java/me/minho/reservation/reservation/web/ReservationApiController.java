package me.minho.reservation.reservation.web;

import lombok.RequiredArgsConstructor;
import me.minho.reservation.reservation.service.ReservationService;
import me.minho.reservation.reservation.web.dto.ReservationResponse;
import me.minho.reservation.util.RestResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReservationApiController {

    private final ReservationService reservationService;

    @GetMapping("/reservations")
    public RestResponseData<List<ReservationResponse>> findAll() {
        return new RestResponseData(HttpStatus.OK, ReservationResponse.of(reservationService.findAll()));
    }
}
