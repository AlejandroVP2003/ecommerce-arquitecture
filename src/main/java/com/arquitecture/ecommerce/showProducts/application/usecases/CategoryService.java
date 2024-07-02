package com.arquitecture.ecommerce.showProducts.application.usecases;

import java.util.List;

import com.arquitecture.ecommerce.showProducts.domain.models.Category;

public interface CategoryService {

    void createCategory(Category category);
    List<Category> listMainCategories();
    List<Category> listAllCategories();
    Category listCategory(Long id);
    Category modifyCategory(Long id);

}
