package com.arquitecture.ecommerce.addProductsInShopCar.infrastructure.mappers;

import com.arquitecture.ecommerce.addProductsInShopCar.domain.models.ShopCar;
import com.arquitecture.ecommerce.addProductsInShopCar.infrastructure.entities.ShopCarEntity;
import org.springframework.stereotype.Component;

@Component
public class ShopCarMapper {
    public ShopCar toDomain(ShopCarEntity entity) {
        ShopCar shopCar = new ShopCar();
        shopCar.setId(entity.getId());
        // Map other fields
        return shopCar;
    }

    public ShopCarEntity toEntity(ShopCar shopCar) {
        ShopCarEntity entity = new ShopCarEntity();
        entity.setId(shopCar.getId());
        // Map other fields
        return entity;
    }
}

