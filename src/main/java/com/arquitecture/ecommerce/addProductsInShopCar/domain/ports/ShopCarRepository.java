package com.arquitecture.ecommerce.addProductsInShopCar.domain.ports;

import com.arquitecture.ecommerce.addProductsInShopCar.domain.models.ShopCar;

import java.util.Optional;

public interface ShopCarRepository {
    Optional<ShopCar> findById(Long id);
    ShopCar save(ShopCar shopCar);
}
