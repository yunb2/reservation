package me.minho.reservation.member.service;

import lombok.RequiredArgsConstructor;
import me.minho.reservation.member.domain.Member;
import me.minho.reservation.member.domain.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long signUp(Member member) {
        if (memberRepository.findByEmail(member.getEmail()) != null) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다");
        }
        return memberRepository.save(member).getId();
    }
}
