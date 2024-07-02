package com.arquitecture.ecommerce.showProducts.domain.ports;

import java.util.List;

import com.arquitecture.ecommerce.showProducts.domain.models.Product;

public interface ProductPersistance {
    
    List<Product> getAllProducts();
    Product getProductById(Long id);
    
}
