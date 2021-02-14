package me.minho.reservation.reservation.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.minho.reservation.reservation.domain.Shop;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ShopSaveRequest {

    private String name;
    private String contact;
    private String address;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime openTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime closeTime;
    private int interval;

    public Shop toShop() {
        return Shop.builder()
                .name(name)
                .contact(contact)
                .address(address)
                .description(description)
                .openTime(openTime)
                .closeTime(closeTime)
                .interval(interval)
                .build();
    }
}
