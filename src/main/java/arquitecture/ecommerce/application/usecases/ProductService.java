package arquitecture.ecommerce.application.usecases;

import java.util.List;

import arquitecture.ecommerce.domain.models.Category;
import arquitecture.ecommerce.domain.models.Product;
import arquitecture.ecommerce.domain.models.User;

public interface ProductService {

    List<Product> listAllProducts();
    Product listProduct(Long id);

    List<Product> findByName(String name);
    List<Product> listLastSixProducts();
    List<Product> listProductsByCategory(Category category);
    List<Product> listProductsByOwner(User user);

    void addProduct(Product product);
    void modifyProduct(Long productId, Category newCategory, int newStock, boolean newIsVisible);

}
