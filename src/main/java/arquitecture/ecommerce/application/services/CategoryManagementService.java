package arquitecture.ecommerce.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import arquitecture.ecommerce.domain.models.Category;
import arquitecture.ecommerce.domain.ports.CategoryPersistance;
import arquitecture.ecommerce.application.usecases.CategoryService;

@Service
public class CategoryManagementService implements CategoryService {

    private final CategoryPersistance categoryPersistance;

    public CategoryManagementService(CategoryPersistance categoryPersistance) {
        this.categoryPersistance = categoryPersistance;
    }

    @Override
    public List<Category> listMainCategories() {
        return categoryPersistance.getAllMainCategories();
    }

    @Override
    public List<Category> listAllCategories() {
        return categoryPersistance.getAllCategories();
    }

    @Override
    public Category listCategoryById(Long id) {
        return categoryPersistance.getCategoryById(id);
    }

}
