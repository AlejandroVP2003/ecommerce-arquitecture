package arquitecture.ecommerce.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import arquitecture.ecommerce.application.usecases.ProductService;
import arquitecture.ecommerce.domain.models.Category;
import arquitecture.ecommerce.domain.models.Product;
import arquitecture.ecommerce.domain.models.User;
import arquitecture.ecommerce.domain.ports.ProductPersistance;

@Service
public class ProductManagementService implements ProductService {

    private final ProductPersistance productPersistance;

    public ProductManagementService(ProductPersistance productPersistance) {
        this.productPersistance = productPersistance;
    }

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

    @Override
    public void addProduct(Product product) {
        productPersistance.saveProduct(product);
    }

    @Override
    public void modifyProduct(Long productId, Category newCategory, int newStock, boolean newIsVisible) {
        productPersistance.updateProduct(productId, newCategory, newStock, newIsVisible);
    }

    @Override
    public List<Product> listProductsByOwner(User user) {
        return productPersistance.getProductsByOwner(user);
    }

}
