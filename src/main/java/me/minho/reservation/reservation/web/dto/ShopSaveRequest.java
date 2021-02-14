package me.minho.reservation.reservation.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.minho.reservation.member.domain.Member;
import me.minho.reservation.reservation.domain.Shop;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class ShopSaveRequest {

    private String name;
    private String contact;
    private String address;
    private String description;
    private LocalTime openTime;
    private LocalTime closeTime;
    private int interval;

    public Shop toShop(Member owner) {
        return Shop.builder()
                .name(name)
                .contact(contact)
                .address(address)
                .description(description)
                .openTime(openTime)
                .closeTime(closeTime)
                .interval(interval)
                .owner(owner)
                .build();
    }
}
