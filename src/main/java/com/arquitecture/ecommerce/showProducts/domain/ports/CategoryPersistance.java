package com.arquitecture.ecommerce.domain.ports;

import java.util.List;

import com.arquitecture.ecommerce.domain.models.Category;

public interface CategoryPersistance {

    void saveCategory(Category category);
    List<Category> getMainCategories();
    List<Category> getAllCategories();
    Category getCategory(Long id);
    Category updateCategory(Long id);

}