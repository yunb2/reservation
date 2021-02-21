package me.minho.reservation.shop.service;

import lombok.RequiredArgsConstructor;
import me.minho.reservation.member.domain.Member;
import me.minho.reservation.reservation.domain.Reservation;
import me.minho.reservation.shop.domain.Shop;
import me.minho.reservation.shop.domain.Timetable;
import me.minho.reservation.shop.controller.dto.ShopInfo;
import me.minho.reservation.member.repository.MemberRepository;
import me.minho.reservation.reservation.repository.ReservationRepository;
import me.minho.reservation.shop.repository.ShopRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ShopService {

    private final MemberRepository memberRepository;
    private final ShopRepository shopRepository;
    private final ReservationRepository reservationRepository;

    public void saveShop(long id, ShopInfo shopInfo) {
        final Member member = memberRepository.findById(id).orElseThrow();
        if (!member.isAdmin()) {
            throw new IllegalArgumentException("관리자 계정이 아닙니다.");
        }
        shopRepository.save(shopInfo.toShop(member));
    }

    public List<ShopInfo> getList() {
        final List<Shop> shopList = shopRepository.findAll();
        return shopList.stream().map(Shop::summarize).collect(Collectors.toList());
    }

    public Timetable getTimetable(long id, LocalDate date) {
        final Shop shop = shopRepository.findById(id).orElseThrow();
        final List<Reservation> reservationList =
                reservationRepository.findAllByShopIdAndStartTimeAfterAndStartTimeBefore(id, date.atStartOfDay(), date.plusDays(1).atStartOfDay());
        return shop.createTimetable(date, reservationList);
    }
}
