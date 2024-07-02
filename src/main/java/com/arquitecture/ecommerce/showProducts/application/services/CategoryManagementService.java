package com.arquitecture.ecommerce.showProducts.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arquitecture.ecommerce.showProducts.domain.models.Category;
import com.arquitecture.ecommerce.showProducts.domain.ports.CategoryPersistance;
import com.arquitecture.ecommerce.showProducts.application.usecases.CategoryService;

@Service
public class CategoryManagementService implements CategoryService {

    @Autowired
    private CategoryPersistance categoryPersistance;

    @Override
    public void createCategory(Category category) {
        categoryPersistance.saveCategory(category);
    }

    @Override
    public List<Category> listMainCategories() {
        return categoryPersistance.getMainCategories();
    }

    @Override
    public List<Category> listAllCategories() {
        return categoryPersistance.getAllCategories();
    }

    @Override
    public Category listCategory(Long id) {
        return categoryPersistance.getCategory(id);
    }

    @Override
    public Category modifyCategory(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modifyCategory'");
    }

}
