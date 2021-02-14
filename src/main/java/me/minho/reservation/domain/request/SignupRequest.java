package me.minho.reservation.domain.request;

import lombok.*;
import me.minho.reservation.domain.Member;
import me.minho.reservation.domain.MemberType;
import me.minho.reservation.util.HashUtil;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Data
public class SignupRequest {
    private String email;
    private String password;
    private String name;
    private MemberType memberType;

    public Member toMember() throws InvalidKeySpecException, NoSuchAlgorithmException {
        return new Member(email.toLowerCase(), HashUtil.hashPassword(password), name, memberType);
    }
}
