package me.minho.reservation.reservation.domain;

import me.minho.reservation.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByShopIdAndStartTimeAfterAndStartTimeBefore(long shopId, LocalDateTime startTime, LocalDateTime nextStartTime);

    Reservation findByShopIdAndStartTimeAndReservationStatus(long shopId, LocalDateTime startTime, ReservationStatus status);

    List<Reservation> findAllByMember(Member member);
}
