package com.arquitecture.ecommerce.addProduct.infrastructure;

import com.arquitecture.ecommerce.addProduct.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
