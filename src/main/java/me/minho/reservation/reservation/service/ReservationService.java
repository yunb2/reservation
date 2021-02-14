package me.minho.reservation.reservation.service;

import lombok.RequiredArgsConstructor;
import me.minho.reservation.reservation.domain.Reservation;
import me.minho.reservation.reservation.domain.ReservationRepository;
import me.minho.reservation.reservation.domain.ReservationStatus;
import me.minho.reservation.reservation.domain.ReservationType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public List<Reservation> findOneDayReservationList(long shopId, LocalDateTime dateTime) {
        return reservationRepository.findAllByShopIdAndStartTimeAfterAndStartTimeBefore(shopId, dateTime, dateTime.plusDays(1));
    }

    @Transactional
    public long save(Reservation reservation) {
        Reservation completedReservation = findByShopIdAndStartTimeAndStatus(reservation.getShop().getId(), reservation.getStartTime(), ReservationStatus.COMPLETED);
        if (completedReservation != null) {
            throw new IllegalStateException("이미 예약 완료건이 존재합니다");
        }

        return reservationRepository.save(reservation).getId();
    }

    private Reservation findByShopIdAndStartTimeAndStatus(long shopId, LocalDateTime startTime, ReservationStatus status) {
        return reservationRepository.findByShopIdAndStartTimeAndReservationStatus(shopId, startTime, status);
    }
}
