package me.minho.reservation.reservation.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.minho.reservation.member.domain.Member;
import me.minho.reservation.shop.domain.Shop;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Table(name = "RESERVATION")
public class Reservation {

    @Id @GeneratedValue
    @Column(name = "RESERVATION_ID")
    private long id;

    @Getter
    @Column(name = "START_TIME", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "END_TIME", nullable = false)
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "RESERVATION_STATUS", nullable = false)
    private ReservationStatus reservationStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "RESERVATION_TYPE", nullable = false)
    private ReservationType reservationType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SHOP_ID")
    private Shop shop;

    private Reservation(LocalDateTime reservationTime, Member member, Shop shop) {
        this.startTime = reservationTime;
        this.endTime = reservationTime.plusMinutes(shop.getInterval());
        this.reservationStatus = ReservationStatus.READY;
        this.reservationType = ReservationType.NORMAL;
        this.member = member;
        this.shop = shop;
    }

    public static Reservation of (Shop shop, Member member, LocalDateTime reservationTime) {
        return new Reservation(reservationTime, member, shop);
    }
}
