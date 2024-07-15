package arquitecture.ecommerce.application.usecases;

import java.util.List;

import arquitecture.ecommerce.domain.models.Category;
import arquitecture.ecommerce.domain.models.Product;

public interface ProductService {

    List<Product> listAllProducts();
    Product listProduct(Long id);
    List<Product> findByName(String name);

    List<Product> listLastSixProducts();
    List<Product> listProductsByCategory(Category category);

}
