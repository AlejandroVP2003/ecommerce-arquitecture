package com.arquitecture.ecommerce.addProductsInShopCar.infrastructure.controllers;

import com.arquitecture.ecommerce.addProductsInShopCar.application.usecases.AddProductToShopCarService;
import com.arquitecture.ecommerce.addProductsInShopCar.domain.models.ShopCar;
import com.arquitecture.ecommerce.addProductsInShopCar.domain.models.ShopCarItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopcar")
public class ShopCarController {
    @Autowired
    private AddProductToShopCarService addProductToShopCarService;

    @PostMapping("/{shopCarId}/add")
    public ShopCar addProductToShopCar(@PathVariable Long shopCarId, @RequestBody ShopCarItem item) {
        return addProductToShopCarService.execute(shopCarId, item);
    }
}
