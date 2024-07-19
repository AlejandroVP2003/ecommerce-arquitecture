package arquitecture.ecommerce.infrastructure.adapters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import arquitecture.ecommerce.domain.models.Category;
import arquitecture.ecommerce.domain.ports.CategoryPersistance;
import arquitecture.ecommerce.infrastructure.entities.CategoryEntity;
import arquitecture.ecommerce.infrastructure.mappers.CategoryMapper;
import arquitecture.ecommerce.infrastructure.repositories.CategoryRepository;

@Repository
public class CategoryJpa implements CategoryPersistance {

    private final CategoryRepository categoryRepository;

    public CategoryJpa(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllMainCategories() {
        List<CategoryEntity> categoryEntities = categoryRepository.findByParentCategoryIsNull();
        List<Category> categories = new ArrayList<>();
        for (CategoryEntity categoryEntity : categoryEntities) {
            categories.add(CategoryMapper.toModel(categoryEntity));
        }
        return categories;
    }

    @Override
    public List<Category> getAllCategories() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        List<Category> categories = new ArrayList<>();
        for (CategoryEntity categoryEntity : categoryEntities) {
            categories.add(CategoryMapper.toModel(categoryEntity));
        }
        return categories;
    }

    @Override
    public Category getCategoryById(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        return CategoryMapper.toModel(categoryEntity);
    }

}
