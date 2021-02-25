package me.minho.reservation.shop.service;

import lombok.RequiredArgsConstructor;
import me.minho.reservation.member.domain.Member;
import me.minho.reservation.member.service.MemberService;
import me.minho.reservation.reservation.domain.Reservation;
import me.minho.reservation.reservation.service.ReservationService;
import me.minho.reservation.shop.controller.dto.ShopInfo;
import me.minho.reservation.shop.domain.Shop;
import me.minho.reservation.shop.domain.Timetable;
import me.minho.reservation.shop.repository.ShopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class ShopService {

    private final MemberService memberService;
    private final ReservationService reservationService;

    private final ShopRepository shopRepository;

    public void saveShop(long memberId, ShopInfo shopInfo) {
        final Member member = memberService.findMemberById(memberId);
        if (!member.isAdmin()) {
            throw new IllegalArgumentException("관리자 계정이 아닙니다.");
        }
        shopRepository.save(shopInfo.toShop(member));
    }

    @Transactional(readOnly = true)
    public List<ShopInfo> getList() {
        final List<Shop> shopList = shopRepository.findAll();
        return shopList.stream().map(Shop::summarize).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Timetable getTimetable(long shopId, LocalDate date) {
        final Shop shop = shopRepository.findById(shopId).orElseThrow();
        final List<Reservation> reservationList = reservationService.getReservationList(shopId, date);
        return shop.createTimetable(date, reservationList);
    }

    public void makeReservation(long shopId, long memberId, LocalDateTime reservationTime) {
        final Member member = memberService.findMemberById(memberId);
        final Shop shop = shopRepository.findById(shopId).orElseThrow();
        if (!shop.isOpenAt(reservationTime.toLocalTime())) {
            throw new IllegalArgumentException("운영시간이 아닙니다.");
        }
        reservationService.makeReservation(shop, member, reservationTime);
    }
}
