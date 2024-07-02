package com.arquitecture.ecommerce.showProducts.infrastructure.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.arquitecture.ecommerce.showProducts.infrastructure.entities.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @Query("SELECT category FROM CategoryEntity category WHERE category.parentCategory IS NULL")
    List<CategoryEntity> findMainCategories();
    
}
