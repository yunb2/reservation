package me.minho.reservation.member.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.minho.reservation.member.domain.Member;
import me.minho.reservation.member.domain.MemberType;
import me.minho.reservation.shop.domain.Shop;
import me.minho.reservation.shop.web.dto.ShopSaveRequest;

@Getter
@NoArgsConstructor
public class MemberSaveRequest {

    private String email;
    private String password;
    private String name;
    private MemberType memberType;

    @JsonProperty(value = "shop")
    private ShopSaveRequest shopSaveRequest;

    public Member toMember() {
        Member member = Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .memberType(memberType)
                .build();

        if (member.isAdmin() && shopSaveRequest == null) {
            throw new IllegalArgumentException("관리자는 샵 정보가 필요합니다");
        }

        if (member.isAdmin()) {
            Shop shop = shopSaveRequest.toShop(member);
            member.setShop(shop);
        }
        return member;
    }
}
