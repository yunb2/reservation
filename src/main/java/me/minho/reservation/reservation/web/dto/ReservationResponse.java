package me.minho.reservation.reservation.web.dto;

import lombok.Builder;
import lombok.Getter;
import me.minho.reservation.reservation.domain.Reservation;
import me.minho.reservation.reservation.domain.ReservationStatus;
import me.minho.reservation.reservation.domain.ReservationType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class ReservationResponse {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ReservationStatus status;
    private ReservationType type;

    public static ReservationResponse of(Reservation reservation) {
        return ReservationResponse.builder()
                .startTime(reservation.getStartTime())
                .endTime(reservation.getEndTime())
                .status(reservation.getReservationStatus())
                .type(reservation.getReservationType())
                .build();
    }

    public static List<ReservationResponse> of(List<Reservation> reservationList) {
        return reservationList.stream()
                .map(reservation -> of(reservation))
                .collect(Collectors.toList());
    }
}
