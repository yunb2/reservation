package me.minho.reservation.controller;

import lombok.RequiredArgsConstructor;
import me.minho.reservation.domain.request.ShopInfo;
import me.minho.reservation.domain.response.RestResponse;
import me.minho.reservation.domain.response.ResultCode;
import me.minho.reservation.service.ShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ShopController {

    private final ShopService shopService;

    @PostMapping("/members/{id}/shop")
    public ResponseEntity<RestResponse<String>> saveShop(@PathVariable long id, @RequestBody ShopInfo shopInfo) {
        try {
            shopService.saveShop(id, shopInfo);
            return new RestResponse<>(ResultCode.SUCCESS,"").toResponseEntity(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new RestResponse<>(ResultCode.BAD_REQUEST,"").toResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
