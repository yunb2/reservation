package me.minho.reservation.reservation.service;

import lombok.RequiredArgsConstructor;
import me.minho.reservation.member.domain.Member;
import me.minho.reservation.reservation.domain.Reservation;
import me.minho.reservation.reservation.repository.ReservationRepository;
import me.minho.reservation.shop.domain.Shop;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public List<Reservation> getReservationList(long shopId, LocalDate date) {
        return reservationRepository.findAllByShopIdAndStartTimeAfterAndStartTimeBefore(shopId, date.atStartOfDay(), date.plusDays(1).atStartOfDay());
    }

    public void makeReservation(Shop shop, Member member, LocalDateTime reservationTime) {
        reservationRepository.save(Reservation.of(shop, member, reservationTime));
    }

    public List<Reservation> getMyReservationList(long memberId) {
        return reservationRepository.findAllByMemberId(memberId);
    }
}
