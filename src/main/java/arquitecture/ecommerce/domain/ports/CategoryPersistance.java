package arquitecture.ecommerce.domain.ports;

import java.util.List;

import arquitecture.ecommerce.domain.models.Category;

public interface CategoryPersistance {

    List<Category> getAllMainCategories();
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
}
