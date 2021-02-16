package me.minho.reservation.shop.controller.dto;

import lombok.Data;
import me.minho.reservation.member.domain.Member;
import me.minho.reservation.shop.domain.Shop;

import java.time.LocalDateTime;

@Data
public class ShopInfo {
    private String name;
    private String contact;
    private String address;
    private String description;
    private LocalDateTime openTime;
    private LocalDateTime closeTime;
    private int interval;

    public Shop toShop(Member owner) {
        if (!owner.isAdmin()) {
            throw new IllegalArgumentException("관리자 계정이 아닙니다.");
        }
        return new Shop(name, contact, address, description, openTime, closeTime, interval, owner);
    }

    public ShopInfo(String name, String contact, String address, String description, LocalDateTime openTime, LocalDateTime closeTime) {
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.description = description;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }
}
