package me.minho.reservation.member.web.dto;

import lombok.Builder;
import lombok.Getter;
import me.minho.reservation.member.domain.Member;
import me.minho.reservation.member.domain.MemberType;

@Getter
@Builder
public class MemberResponse {

    private long id;
    private String email;
    private String name;
    private MemberType memberType;

    public static MemberResponse of(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .memberType(member.getMemberType())
                .build();
    }
}
