package arquitecture.ecommerce.domain.ports;

import java.util.List;

import arquitecture.ecommerce.domain.models.Category;
import arquitecture.ecommerce.domain.models.Product;
import arquitecture.ecommerce.domain.models.User;

public interface ProductPersistance {
    
    List<Product> getAllProducts();
    Product getProductById(Long id);

    List<Product> getProductsByName(String name);
    List<Product> getLastSixProducts();
    List<Product> getProductsByCategory(Category category);
    List<Product> getProductsByOwner(User owner);

    void saveProduct(Product product);
    void updateProduct(Long productId, Category newCategory, int newStock, boolean newIsVisible);
    
}
