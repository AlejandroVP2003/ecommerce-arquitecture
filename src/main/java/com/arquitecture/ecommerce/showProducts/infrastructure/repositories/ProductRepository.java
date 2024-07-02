package com.arquitecture.ecommerce.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arquitecture.ecommerce.infrastructure.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
