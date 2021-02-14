package me.minho.reservation.reservation.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.minho.reservation.member.domain.Member;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "SHOP")
public class Shop {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "OPEN_TIME", nullable = false)
    private LocalDateTime openTime;

    @Column(name = "CLOSE_TIME", nullable = false)
    private LocalDateTime closeTime;

    @Column(name = "RESERVATION_TIME_INTERVAL", nullable = false)
    private int interval;

    @Setter
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member owner;

    @Builder
    public Shop(String name, String contact, String address, String description, LocalDateTime openTime, LocalDateTime closeTime, int interval) {
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.description = description;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.interval = interval;
    }
}
