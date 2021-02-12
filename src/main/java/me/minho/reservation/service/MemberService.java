package me.minho.reservation.service;

import me.minho.reservation.domain.Member;
import me.minho.reservation.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member save(Member testMember) {
        return memberRepository.save(testMember);
    }
}
