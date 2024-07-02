package com.arquitecture.ecommerce.infrastructure.adapters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arquitecture.ecommerce.infrastructure.mappers.CategoryMapper;
import com.arquitecture.ecommerce.domain.models.Category;
import com.arquitecture.ecommerce.domain.ports.CategoryPersistance;
import com.arquitecture.ecommerce.infrastructure.entities.CategoryEntity;
import com.arquitecture.ecommerce.infrastructure.repositories.CategoryRepository;

@Repository
public class CategoryJpa implements CategoryPersistance {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void saveCategory(Category category) {
        CategoryEntity categoryEntity = CategoryMapper.toEntity(category);
        categoryRepository.save(categoryEntity);
    }

    @Override
    public List<Category> getMainCategories() {
        List<CategoryEntity> categoryEntities = categoryRepository.findMainCategories(); 
        
        List<Category> categories = new ArrayList<>();
        for (CategoryEntity entity : categoryEntities) {
            categories.add(CategoryMapper.toDomain(entity));
        }

        return categories;
    }

    @Override
    public List<Category> getAllCategories() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll(); 
        
        List<Category> categories = new ArrayList<>();
        for (CategoryEntity entity : categoryEntities) {
            categories.add(CategoryMapper.toDomain(entity));
        }

        return categories;
    }

    @Override
    public Category getCategory(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElse(null);
        return categoryEntity != null ? CategoryMapper.toDomain(categoryEntity) : null;
    }

    @Override
    public Category updateCategory(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCategory'");
    }
    
}
