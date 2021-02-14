package me.minho.reservation.reservation.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import me.minho.reservation.member.web.dto.MemberResponse;
import me.minho.reservation.reservation.domain.Shop;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class ShopResponse {
    private long id;
    private String name;
    private String contact;
    private String address;
    private String description;
    private LocalTime openTime;
    private LocalTime closeTime;
    private int interval;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MemberResponse owner;

    public static ShopResponse of(Shop shop) {
        return ShopResponse.builder()
                .id(shop.getId())
                .name(shop.getName())
                .contact(shop.getContact())
                .address(shop.getAddress())
                .description(shop.getDescription())
                .openTime(shop.getOpenTime())
                .closeTime(shop.getCloseTime())
                .interval(shop.getInterval())
                .owner(MemberResponse.of(shop.getOwner()))
                .build();
    }

    public static List<ShopResponse> of(List<Shop> shopList) {
        return shopList.stream()
                .map(shop -> ShopResponse.of(shop))
                .collect(Collectors.toList());
    }
}
