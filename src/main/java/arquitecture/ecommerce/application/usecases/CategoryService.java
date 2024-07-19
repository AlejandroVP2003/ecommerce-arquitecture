package arquitecture.ecommerce.application.usecases;

import java.util.List;

import arquitecture.ecommerce.domain.models.Category;

public interface CategoryService {

    List<Category> listMainCategories();
    List<Category> listAllCategories();

    Category listCategoryById(Long id);
    
}
