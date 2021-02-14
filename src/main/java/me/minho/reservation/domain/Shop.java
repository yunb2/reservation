package me.minho.reservation.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member owner;

    public Shop(String name, String contact, String address, String description, LocalDateTime openTime, LocalDateTime closeTime, int interval, Member owner) {
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.description = description;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.interval = interval;
        this.owner = owner;
    }
}
