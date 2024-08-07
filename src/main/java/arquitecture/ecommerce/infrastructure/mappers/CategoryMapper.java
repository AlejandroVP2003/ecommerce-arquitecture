package arquitecture.ecommerce.infrastructure.mappers;

import java.util.HashSet;
import java.util.Set;

import arquitecture.ecommerce.domain.models.Category;
import arquitecture.ecommerce.infrastructure.entities.CategoryEntity;

public class CategoryMapper {

    public static Category toModel(CategoryEntity entity) {
        return toModel(entity, new HashSet<>());
    }
    
    private static Category toModel(CategoryEntity entity, Set<Long> visitedEntities) {
        if (entity == null || visitedEntities.contains(entity.getId())) {
            return null;
        }
        visitedEntities.add(entity.getId());

        Category category = new Category();
        category.setId(entity.getId());
        category.setName(entity.getName());
    
        if (entity.getParentCategory() != null) {
            category.setParentCategory(toModel(entity.getParentCategory(), visitedEntities));
        }
    
        if (entity.getSubcategories() != null && !entity.getSubcategories().isEmpty()) {
            Set<Category> subcategories = new HashSet<>();
            for (CategoryEntity subcategoryEntity : entity.getSubcategories()) {
                subcategories.add(toModel(subcategoryEntity, visitedEntities));
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
