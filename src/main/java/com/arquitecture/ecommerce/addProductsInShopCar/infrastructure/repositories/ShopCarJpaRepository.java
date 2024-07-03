package com.arquitecture.ecommerce.addProductsInShopCar.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopCarJpaRepository extends JpaRepository<ShopCarEntity, Long> {
}
