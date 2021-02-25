package me.minho.reservation.reservation.service;

import lombok.RequiredArgsConstructor;
import me.minho.reservation.member.domain.Member;
import me.minho.reservation.reservation.controller.dto.ReservationInfo;
import me.minho.reservation.reservation.domain.Reservation;
import me.minho.reservation.reservation.repository.ReservationRepository;
import me.minho.reservation.shop.domain.Shop;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Transactional(readOnly = true)
    public List<Reservation> getReservationList(long shopId, LocalDate date) {
        return reservationRepository.findAllByShopIdAndStartTimeAfterAndStartTimeBefore(shopId, date.atStartOfDay(), date.plusDays(1).atStartOfDay());
    }

    public void makeReservation(Shop shop, Member member, LocalDateTime reservationTime) {
        final List<Reservation> reservationList = reservationRepository.findAllByShopIdAndStartTime(shop.getId(), reservationTime);
        if (!CollectionUtils.isEmpty(reservationList)) {
            throw new IllegalStateException("예약 가능한 시간이 아닙니다.");
        }
        reservationRepository.save(Reservation.of(shop, member, reservationTime));
    }

    @Transactional(readOnly = true)
    public List<Reservation> getMyReservationList(long memberId) {
        return reservationRepository.findAllByMemberId(memberId);
    }

    public void updateReservation(long memberId, long reservationId, ReservationInfo reservationInfo) {
        final Reservation reservation = reservationRepository.findById(reservationId).orElseThrow();
        if (!reservation.isReservedBy(memberId)) {
            throw new IllegalArgumentException("예약을 수정할 권한이 없습니다.");
        }

        final Shop shop = reservation.getShop();
        final LocalDateTime reservationTime = reservationInfo.getReservationTime();
        if (!shop.isOpenAt(reservationTime.toLocalTime())) {
            throw new IllegalStateException("예약 가능한 시간이 아닙니다.");
        }

        final List<Reservation> reservationList = reservationRepository.findAllByShopIdAndStartTime(shop.getId(), reservationTime);
        if (!CollectionUtils.isEmpty(reservationList.stream().filter(Reservation::isReady).collect(Collectors.toList()))) {
            throw new IllegalStateException("예약 가능한 시간이 아닙니다.");
        }

        reservation.update(reservationInfo);
    }
}
