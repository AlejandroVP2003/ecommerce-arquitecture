package arquitecture.ecommerce.infrastructure.adapters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import arquitecture.ecommerce.domain.models.Category;
import arquitecture.ecommerce.domain.models.Product;
import arquitecture.ecommerce.domain.models.User;
import arquitecture.ecommerce.domain.ports.ProductPersistance;
import arquitecture.ecommerce.infrastructure.entities.ProductEntity;
import arquitecture.ecommerce.infrastructure.mappers.CategoryMapper;
import arquitecture.ecommerce.infrastructure.mappers.ProductMapper;
import arquitecture.ecommerce.infrastructure.mappers.UserMapper;
import arquitecture.ecommerce.infrastructure.repositories.ProductRepository;

@Repository
public class ProductJpa implements ProductPersistance {
   
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        List<ProductEntity> productEntities = productRepository.findByIsVisibleTrue(); 
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
        List<ProductEntity> productEntities = productRepository.findByNameContainingAndIsVisibleTrue(name);
        List<Product> products = new ArrayList<>();
        for (ProductEntity entity : productEntities) {
            products.add(ProductMapper.toDomain(entity, true));
        }
        return products;
    }

    @Override
    public List<Product> getLastSixProducts() {
        List<ProductEntity> productEntities = productRepository.findTop6ByIsVisibleTrueOrderByReleaseDateDesc(); 
        List<Product> products = new ArrayList<>();
        for (ProductEntity entity : productEntities) {
            products.add(ProductMapper.toDomain(entity, true));
        }
        return products;
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        List<ProductEntity> productEntities = productRepository.findByCategoryAndIsVisibleTrue(CategoryMapper.toEntity(category));
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

    @Override
    public void updateProduct(Long productId, Category newCategory, int newStock, boolean newIsVisible) {
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow();
        productEntity.setCategory(CategoryMapper.toEntity(newCategory));
        productEntity.setStock(newStock);
        productEntity.setVisible(newIsVisible);
        productRepository.save(productEntity);
    }

    @Override
    public List<Product> getProductsByOwner(User owner) {
        List<ProductEntity> productEntities = productRepository.findByOwner(UserMapper.toEntity(owner, true));
        List<Product> products = new ArrayList<>();
        for (ProductEntity entity : productEntities) {
            products.add(ProductMapper.toDomain(entity, true));
        }
        return products;
    }

    
        
}
