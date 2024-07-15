package arquitecture.ecommerce.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arquitecture.ecommerce.application.usecases.ProductService;
import arquitecture.ecommerce.domain.models.Category;
import arquitecture.ecommerce.domain.models.Product;
import arquitecture.ecommerce.domain.ports.ProductPersistance;

@Service
public class ProductManagementService implements ProductService {

    @Autowired
    private ProductPersistance productPersistance;

    @Override
    public List<Product> listAllProducts() {
        return productPersistance.getAllProducts();
    }

    @Override
    public Product listProduct(Long id) {
        return productPersistance.getProductById(id);
    }

    @Override
    public List<Product> findByName(String name) {
        return productPersistance.getProductsByName(name);
    }

    @Override
    public List<Product> listLastSixProducts() {
        return productPersistance.getLastSixProducts();
    }

    @Override
    public List<Product> listProductsByCategory(Category category) {
        return productPersistance.getProductsByCategory(category);
    }

}
