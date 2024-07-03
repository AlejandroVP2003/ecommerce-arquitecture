
package com.arquitecture.ecommerce.addProductsInShopCar.application.services;

import com.arquitecture.ecommerce.addProductsInShopCar.domain.models.ShopCar;
import com.arquitecture.ecommerce.addProductsInShopCar.domain.models.ShopCarItem;
import com.arquitecture.ecommerce.addProductsInShopCar.domain.ports.ShopCarRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShopCarManagementService {
    private final ShopCarRepository shopCarRepository;

    public ShopCarManagementService(ShopCarRepository shopCarRepository) {
        this.shopCarRepository = shopCarRepository;
    }

    public ShopCar addProductToShopCar(Long shopCarId, ShopCarItem item) {
        Optional<ShopCar> shopCar = shopCarRepository.findById(shopCarId);
        if (shopCar.isPresent()) {
            ShopCar car = shopCar.get();
            car.addItem(item);
            return shopCarRepository.save(car);
        } else {
            // Handle the case where the shop car is not found
            // Optionally, create a new shop car
            ShopCar newCar = new ShopCar();
            newCar.addItem(item);
            return shopCarRepository.save(newCar);
        }
    }
}

