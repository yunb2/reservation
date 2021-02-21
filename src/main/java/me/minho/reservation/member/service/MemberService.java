package me.minho.reservation.member.service;

import lombok.RequiredArgsConstructor;
import me.minho.reservation.member.domain.Member;
import me.minho.reservation.member.repository.MemberRepository;
import me.minho.reservation.util.HashUtil;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public void signup(Member member) {
        final Member findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember != null) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
        memberRepository.save(member);
    }

    public long login(String email, String passwordInput) {
        final Member findMember = memberRepository.findByEmail(email);
        if (findMember == null) {
            throw new NoSuchElementException("존재하지 않는 회원입니다.");
        }
        if (!HashUtil.validatePassword(passwordInput, findMember.getHashedPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return findMember.getId();
    }

    public Member findMemberById(long memberId) {
        return memberRepository.findById(memberId).orElseThrow();
    }
}
