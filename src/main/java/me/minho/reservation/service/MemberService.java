package me.minho.reservation.service;

import lombok.RequiredArgsConstructor;
import me.minho.reservation.domain.Member;
import me.minho.reservation.repository.MemberRepository;
import me.minho.reservation.util.HashUtil;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public void signup(Member member) {
        final Member findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember != null) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
        memberRepository.save(findMember);
    }

    public long login(String email, String passwordInput) {
        final Member findMember = memberRepository.findByEmail(email);
        if (findMember == null) {
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
        if (!HashUtil.validatePassword(passwordInput, findMember.getHashedPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return findMember.getId();
    }
}
