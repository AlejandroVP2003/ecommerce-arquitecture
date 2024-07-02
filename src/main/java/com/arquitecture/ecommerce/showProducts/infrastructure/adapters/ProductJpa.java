package com.arquitecture.ecommerce.infrastructure.adapters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arquitecture.ecommerce.domain.models.Product;
import com.arquitecture.ecommerce.domain.ports.ProductPersistance;
import com.arquitecture.ecommerce.infrastructure.entities.ProductEntity;
import com.arquitecture.ecommerce.infrastructure.mappers.ProductMapper;
import com.arquitecture.ecommerce.infrastructure.repositories.ProductRepository;

@Repository
public class ProductJpa implements ProductPersistance {
   
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        List<ProductEntity> productEntities = productRepository.findAll(); 
        
        List<Product> products = new ArrayList<>();
        for (ProductEntity entity : productEntities) {
            products.add(ProductMapper.toDomain(entity));
        }

        return products;
    }
    
}
