package com.arquitecture.ecommerce.addProduct.application;

import com.arquitecture.ecommerce.addProduct.domain.Category;
import com.arquitecture.ecommerce.addProduct.infrastructure.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> getCategorys() {
        return categoryRepository.findAll();
    }

}
