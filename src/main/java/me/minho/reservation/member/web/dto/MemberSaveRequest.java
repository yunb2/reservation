package me.minho.reservation.member.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.minho.reservation.member.domain.Member;
import me.minho.reservation.member.domain.MemberType;

@Getter
@NoArgsConstructor
public class MemberSaveRequest {

    private String email;
    private String password;
    private String name;
    private MemberType memberType;

    public Member toMember() {
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .memberType(memberType)
                .build();
    }
}
