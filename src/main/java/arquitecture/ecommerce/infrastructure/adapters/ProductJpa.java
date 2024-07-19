package arquitecture.ecommerce.infrastructure.adapters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import arquitecture.ecommerce.domain.models.Category;
import arquitecture.ecommerce.domain.models.Product;
import arquitecture.ecommerce.domain.ports.ProductPersistance;
import arquitecture.ecommerce.infrastructure.entities.CategoryEntity;
import arquitecture.ecommerce.infrastructure.entities.ProductEntity;
import arquitecture.ecommerce.infrastructure.mappers.CategoryMapper;
import arquitecture.ecommerce.infrastructure.mappers.ProductMapper;
import arquitecture.ecommerce.infrastructure.repositories.ProductRepository;

@Repository
public class ProductJpa implements ProductPersistance {
   
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        List<ProductEntity> productEntities = productRepository.findAll(); 
        List<Product> products = new ArrayList<>();
        for (ProductEntity entity : productEntities) {
            products.add(ProductMapper.toDomain(entity, true));
        }

        return products;
    }

    @Override
    public Product getProductById(Long id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return ProductMapper.toDomain(productEntity, true);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        List<ProductEntity> productEntities = productRepository.findByNameContaining(name);
        List<Product> products = new ArrayList<>();
        for (ProductEntity entity : productEntities) {
            products.add(ProductMapper.toDomain(entity, true));
        }
        return products;
    }

    @Override
    public List<Product> getLastSixProducts() {
        List<ProductEntity> productEntities = productRepository.findTop6ByOrderByReleaseDateDesc(); 
        List<Product> products = new ArrayList<>();
        for (ProductEntity entity : productEntities) {
            products.add(ProductMapper.toDomain(entity, true));
        }
        return products;
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        CategoryEntity categoryEntity = CategoryMapper.toEntity(category);
        List<ProductEntity> productEntities = productRepository.findByCategory(categoryEntity);
        List<Product> products = new ArrayList<>();
        for (ProductEntity entity : productEntities) {
            products.add(ProductMapper.toDomain(entity, true));
        }
        return products;
    }

    @Override
    public void saveProduct(Product product) {
        ProductEntity productEntity = ProductMapper.toEntity(product, true);
        productRepository.save(productEntity);
    }
}
