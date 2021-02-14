package me.minho.reservation.service;

import lombok.RequiredArgsConstructor;
import me.minho.reservation.domain.Member;
import me.minho.reservation.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public void signup(Member member) {
        final List<Member> memberList = memberRepository.findByEmail(member.getEmail());
        if (!CollectionUtils.isEmpty(memberList)) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
        memberRepository.save(member);
    }
}
