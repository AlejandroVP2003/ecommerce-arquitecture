package com.arquitecture.ecommerce.addProduct.application;

import com.arquitecture.ecommerce.addProduct.domain.Category;

import java.util.List;

public interface CategoryService {

    Category getCategoryById(Long id);
    List<Category> getCategorys();
}
