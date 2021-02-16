package me.minho.reservation.member.controller.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
