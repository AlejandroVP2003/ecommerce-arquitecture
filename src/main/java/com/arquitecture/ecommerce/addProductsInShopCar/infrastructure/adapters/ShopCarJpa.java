package com.arquitecture.ecommerce.addProductsInShopCar.infrastructure.adapters;

import com.arquitecture.ecommerce.addProductsInShopCar.domain.models.ShopCar;
import com.arquitecture.ecommerce.addProductsInShopCar.domain.ports.ShopCarRepository;
import com.arquitecture.ecommerce.addProductsInShopCar.infrastructure.entities.ShopCarEntity;
import com.arquitecture.ecommerce.addProductsInShopCar.infrastructure.mappers.ShopCarMapper;
import com.arquitecture.ecommerce.addProductsInShopCar.infrastructure.repositories.ShopCarJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ShopCarJpa implements ShopCarRepository {
    private final ShopCarJpaRepository shopCarJpaRepository;
    private final ShopCarMapper shopCarMapper;

    public ShopCarJpa(ShopCarJpaRepository shopCarJpaRepository, ShopCarMapper shopCarMapper) {
        this.shopCarJpaRepository = shopCarJpaRepository;
        this.shopCarMapper = shopCarMapper;
    }

    @Override
    public Optional<ShopCar> findById(Long id) {
        return shopCarJpaRepository.findById(id).map(shopCarMapper::toDomain);
    }

    @Override
    public ShopCar save(ShopCar shopCar) {
        ShopCarEntity entity = shopCarMapper.toEntity(shopCar);
        return shopCarMapper.toDomain(shopCarJpaRepository.save(entity));
    }
}
