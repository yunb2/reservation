package me.minho.reservation.shop.controller;

import lombok.RequiredArgsConstructor;
import me.minho.reservation.shop.controller.dto.ShopInfo;
import me.minho.reservation.shop.domain.Timetable;
import me.minho.reservation.common.RestResponse;
import me.minho.reservation.common.ResultCode;
import me.minho.reservation.shop.service.ShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class ShopController {

    private final ShopService shopService;

    @PostMapping("/members/{memberId}/shop")
    public ResponseEntity<RestResponse<String>> saveShop(@PathVariable long memberId, @RequestBody ShopInfo shopInfo) {
        try {
            shopService.saveShop(memberId, shopInfo);
            return new RestResponse<>(ResultCode.SUCCESS,"").toResponseEntity(HttpStatus.OK);
        } catch (NoSuchElementException | IllegalArgumentException e) {
            return new RestResponse<>(ResultCode.BAD_REQUEST,"").toResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/shops")
    public ResponseEntity<RestResponse<List<ShopInfo>>> getShopList() {
        final List<ShopInfo> shopInfoList = shopService.getList();
        return new RestResponse<>(ResultCode.SUCCESS, shopInfoList).toResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/shops/{shopId}/timetable")
    public ResponseEntity<RestResponse<Timetable>> getTimetable(@PathVariable long shopId, @RequestParam("date") LocalDate date) {
        try {
            final Timetable timetable = shopService.getTimetable(shopId, date);
            return new RestResponse<>(ResultCode.SUCCESS, timetable).toResponseEntity(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new RestResponse<Timetable>(ResultCode.BAD_REQUEST, null).toResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/shops/{shopId}/reservations")
    public ResponseEntity<RestResponse<String>> makeReservation(@PathVariable long shopId, @RequestParam LocalDateTime reservationTime, HttpSession session) {
        try {
            final long memberId = (long) Optional.ofNullable(session.getAttribute("id")).orElseThrow();
            shopService.makeReservation(shopId, memberId, reservationTime);
            return new RestResponse<>(ResultCode.SUCCESS, "").toResponseEntity(HttpStatus.OK);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return new RestResponse<>(ResultCode.BAD_REQUEST, "").toResponseEntity(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new RestResponse<>(ResultCode.BAD_REQUEST, "").toResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
