package me.minho.reservation.shop.controller.dto;

import lombok.Data;
import me.minho.reservation.member.domain.Member;
import me.minho.reservation.shop.domain.Shop;

import java.time.LocalTime;

@Data
public class ShopInfo {
    private long id;
    private String name;
    private String contact;
    private String address;
    private String description;
    private LocalTime openTime;
    private LocalTime closeTime;
    private int interval;

    public Shop toShop(Member owner) {
        if (!owner.isAdmin()) {
            throw new IllegalArgumentException("관리자 계정이 아닙니다.");
        }
        return new Shop(name, contact, address, description, openTime, closeTime, interval, owner);
    }

    public ShopInfo(long id, String name, String contact, String address, String description, LocalTime openTime, LocalTime closeTime) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.description = description;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }
}
