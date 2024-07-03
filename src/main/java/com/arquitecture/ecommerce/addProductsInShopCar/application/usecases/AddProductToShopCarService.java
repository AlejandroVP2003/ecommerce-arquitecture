
package com.arquitecture.ecommerce.addProductsInShopCar.application.usecases;

import com.arquitecture.ecommerce.addProductsInShopCar.domain.models.ShopCar;
import com.arquitecture.ecommerce.addProductsInShopCar.domain.models.ShopCarItem;
import com.arquitecture.ecommerce.addProductsInShopCar.application.services.ShopCarManagementService;
import org.springframework.stereotype.Service;

@Service
public class AddProductToShopCarService {
    private final ShopCarManagementService shopCarManagementService;

    public AddProductToShopCarService(ShopCarManagementService shopCarManagementService) {
        this.shopCarManagementService = shopCarManagementService;
    }

    public ShopCar execute(Long shopCarId, ShopCarItem item) {
        return shopCarManagementService.addProductToShopCar(shopCarId, item);
    }
}

