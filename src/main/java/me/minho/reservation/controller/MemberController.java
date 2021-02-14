package me.minho.reservation.controller;

import lombok.RequiredArgsConstructor;
import me.minho.reservation.domain.request.LoginRequest;
import me.minho.reservation.domain.request.SignupRequest;
import me.minho.reservation.domain.response.RestResponse;
import me.minho.reservation.domain.response.ResultCode;
import me.minho.reservation.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<RestResponse<String>> signup(@RequestBody SignupRequest request) {
        try {
            memberService.signup(request.toMember());
            return new RestResponse<>(ResultCode.SUCCESS,"").toResponseEntity(HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new RestResponse<>(ResultCode.ALREADY_EXISTS, "").toResponseEntity(HttpStatus.OK);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            return new RestResponse<>(ResultCode.INTERNAL_SERVER_ERROR, "").toResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<RestResponse<String>> login(@RequestBody LoginRequest request, HttpSession session) {
        try {
            long id = memberService.login(request.getEmail(), request.getPassword());
            session.setAttribute("id", id);
            return new RestResponse<>(ResultCode.SUCCESS, "").toResponseEntity(HttpStatus.OK);
        } catch (NoSuchElementException | IllegalArgumentException e) {
            return new RestResponse<>(ResultCode.NOT_FOUND, "").toResponseEntity(HttpStatus.OK);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<RestResponse<String>> logout(HttpSession session) {
        session.removeAttribute("id");
        return new RestResponse<>(ResultCode.SUCCESS, "").toResponseEntity(HttpStatus.OK);
    }

}
