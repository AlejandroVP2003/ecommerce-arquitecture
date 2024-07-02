package com.arquitecture.ecommerce.domain.ports;

import java.util.List;

import com.arquitecture.ecommerce.domain.models.Product;

public interface ProductPersistance {
    
    List<Product> getAllProducts();
    
}
