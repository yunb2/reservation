package me.minho.reservation.reservation.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.minho.reservation.reservation.domain.ReservationStatus;

@Getter
@NoArgsConstructor
public class ReservationStatusUpdateRequest {
    private ReservationStatus status;
}
