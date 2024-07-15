package arquitecture.ecommerce.domain.ports;

import java.util.List;

import arquitecture.ecommerce.domain.models.Category;
import arquitecture.ecommerce.domain.models.Product;

public interface ProductPersistance {
    
    List<Product> getAllProducts();
    Product getProductById(Long id);
    List<Product> getProductsByName(String name);

    List<Product> getLastSixProducts();
    List<Product> getProductsByCategory(Category category);
}
