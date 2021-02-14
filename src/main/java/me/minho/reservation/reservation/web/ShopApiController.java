package me.minho.reservation.reservation.web;

import lombok.RequiredArgsConstructor;
import me.minho.reservation.reservation.service.ShopService;
import me.minho.reservation.reservation.web.dto.ShopResponse;
import me.minho.reservation.util.RestResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ShopApiController {

    private final ShopService shopService;

    @GetMapping("/shops")
    public RestResponseData<List<ShopResponse>> getShopList() {
        return new RestResponseData(HttpStatus.OK, ShopResponse.of(shopService.getShopList()));
    }
}
