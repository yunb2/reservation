package me.minho.reservation.service;

import lombok.RequiredArgsConstructor;
import me.minho.reservation.domain.Member;
import me.minho.reservation.domain.request.ShopInfo;
import me.minho.reservation.repository.MemberRepository;
import me.minho.reservation.repository.ShopRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShopService {

    private final MemberRepository memberRepository;
    private final ShopRepository shopRepository;

    public void saveShop(long id, ShopInfo shopInfo) {
        final Member member = memberRepository.findById(id).orElseThrow();
        if (!member.isAdmin()) {
            throw new IllegalArgumentException("관리자 계정이 아닙니다.");
        }
        shopRepository.save(shopInfo.toShop(member));
    }
}
