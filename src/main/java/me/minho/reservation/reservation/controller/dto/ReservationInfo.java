package me.minho.reservation.reservation.controller.dto;

import lombok.Data;
import me.minho.reservation.reservation.domain.ReservationStatus;

import java.time.LocalDateTime;

@Data
public class ReservationInfo {
    private LocalDateTime reservationTime;
    private ReservationStatus status;
}
