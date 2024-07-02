package com.arquitecture.ecommerce.infrastructure.mappers;

import java.util.HashSet;
import java.util.Set;

import com.arquitecture.ecommerce.domain.models.Category;
import com.arquitecture.ecommerce.infrastructure.entities.CategoryEntity;

public class CategoryMapper {

    public static Category toDomain(CategoryEntity entity) {
        return toDomain(entity, new HashSet<>());
    }
    
    private static Category toDomain(CategoryEntity entity, Set<Long> visitedEntities) {
        if (entity == null || visitedEntities.contains(entity.getId())) {
            return null;
        }
    
        visitedEntities.add(entity.getId());
    
        Category category = new Category();
        category.setId(entity.getId());
        category.setName(entity.getName());
    
        if (entity.getParentCategory() != null) {
            category.setParentCategory(toDomain(entity.getParentCategory(), visitedEntities));
        }
    
        if (entity.getSubcategories() != null && !entity.getSubcategories().isEmpty()) {
            Set<Category> subcategories = new HashSet<>();
            for (CategoryEntity subcategoryEntity : entity.getSubcategories()) {
                subcategories.add(toDomain(subcategoryEntity, visitedEntities));
            }
            category.setSubcategories(subcategories);
        }
    
        return category;
    }
    
    public static CategoryEntity toEntity(Category category) {
        return toEntity(category, new HashSet<>());
    }
    
    private static CategoryEntity toEntity(Category category, Set<Long> visitedCategories) {
        if (category == null || visitedCategories.contains(category.getId())) {
            return null;
        }
    
        visitedCategories.add(category.getId());
    
        CategoryEntity entity = new CategoryEntity();
        entity.setId(category.getId());
        entity.setName(category.getName());
    
        if (category.getParentCategory() != null) {
            entity.setParentCategory(toEntity(category.getParentCategory(), visitedCategories));
        }
    
        if (category.getSubcategories() != null && !category.getSubcategories().isEmpty()) {
            Set<CategoryEntity> subcategoryEntities = new HashSet<>();
            for (Category subcategory : category.getSubcategories()) {
                subcategoryEntities.add(toEntity(subcategory, visitedCategories));
            }
            entity.setSubcategories(subcategoryEntities);
        }
    
        return entity;
    }
    
}
