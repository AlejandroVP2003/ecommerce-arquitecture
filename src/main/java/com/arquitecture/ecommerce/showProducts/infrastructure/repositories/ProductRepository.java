package com.arquitecture.ecommerce.showProducts.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arquitecture.ecommerce.showProducts.infrastructure.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
