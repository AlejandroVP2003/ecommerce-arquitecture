package com.arquitecture.ecommerce.showProducts.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arquitecture.ecommerce.showProducts.application.usecases.ProductService;
import com.arquitecture.ecommerce.showProducts.domain.models.Product;
import com.arquitecture.ecommerce.showProducts.domain.ports.ProductPersistance;

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
    
}
