package me.minho.reservation.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "SHOP")
public class Shop {

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

    @Column(name = "OPEN_TIME", nullable = false)
    private LocalDateTime openTime;

    @Column(name = "CLOSE_TIME", nullable = false)
    private LocalDateTime closeTime;

    @Column(name = "RESERVATION_INTERVAL", nullable = false)
    private int interval;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member owner;
}
