package me.minho.reservation.reservation.controller;

import lombok.RequiredArgsConstructor;
import me.minho.reservation.common.RestResponse;
import me.minho.reservation.common.ResultCode;
import me.minho.reservation.reservation.domain.Reservation;
import me.minho.reservation.reservation.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/reservations")
    public ResponseEntity<RestResponse<List<Reservation>>> getReservationList(HttpSession session) {
        try {
            final long memberId = (long) Optional.ofNullable(session.getAttribute("id")).orElseThrow();
            final List<Reservation> reservationList = reservationService.getMyReservationList(memberId);
            return new RestResponse<>(ResultCode.SUCCESS, reservationList).toResponseEntity(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new RestResponse<List<Reservation>>(ResultCode.BAD_REQUEST, null).toResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
