package com.arquitecture.ecommerce.application.usecases;

import java.util.List;

import com.arquitecture.ecommerce.domain.models.Product;

public interface ProductService {

    List<Product> listAllProducts();
    
}
