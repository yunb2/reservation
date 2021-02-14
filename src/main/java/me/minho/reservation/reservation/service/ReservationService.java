package me.minho.reservation.reservation.service;

import lombok.RequiredArgsConstructor;
import me.minho.reservation.reservation.domain.Reservation;
import me.minho.reservation.reservation.domain.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public List<Reservation> getReservationList(long shopId, LocalDateTime dateTime) {
        return reservationRepository.findAllByShopIdAndStartTimeAfterAndStartTimeBefore(shopId, dateTime, dateTime.plusDays(1));
    }
}
