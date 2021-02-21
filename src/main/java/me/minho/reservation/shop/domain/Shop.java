package me.minho.reservation.shop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.minho.reservation.member.domain.Member;
import me.minho.reservation.reservation.domain.Reservation;
import me.minho.reservation.shop.controller.dto.ShopInfo;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Entity
@Table(name = "SHOP")
public class Shop {

    @Getter
    @Id @GeneratedValue
    @Column(name = "SHOP_ID")
    private long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CONTACT", nullable = false)
    private String contact;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Lob
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Getter
    @Column(name = "OPEN_TIME", nullable = false)
    private LocalTime openTime;

    @Getter
    @Column(name = "CLOSE_TIME", nullable = false)
    private LocalTime closeTime;

    @Getter
    @Column(name = "RESERVATION_INTERVAL", nullable = false)
    private int interval;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member owner;

    public Shop(String name, String contact, String address, String description, LocalTime openTime, LocalTime closeTime, int interval, Member owner) {
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.description = description;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.interval = interval;
        this.owner = owner;
    }

    public ShopInfo summarize() {
        return new ShopInfo(id, name, contact, address, description, openTime, closeTime);
    }

    public Timetable createTimetable(LocalDate date, List<Reservation> reservationList) {
        final Map<LocalDateTime, Boolean> table = initTable(date);
        reservationList.forEach(reservation -> table.put(reservation.getStartTime(), false));
        return Timetable.of(table);
    }

    private Map<LocalDateTime, Boolean> initTable(LocalDate date) {
        final Map<LocalDateTime, Boolean> table = new LinkedHashMap<>();
        LocalDateTime time = openTime.atDate(date);
        while (time.isBefore(closeTime.atDate(date))) {
            table.put(time, true);
            time = time.plusMinutes(interval);
        }
        return table;
    }

    public boolean isOpenAt(LocalTime reservationTime) {
        return reservationTime.isAfter(openTime.minusMinutes(1)) && reservationTime.isBefore(closeTime.plusMinutes(1));
    }
}
