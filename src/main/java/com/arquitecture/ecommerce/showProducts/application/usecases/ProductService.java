package com.arquitecture.ecommerce.showProducts.application.usecases;

import java.util.List;

import com.arquitecture.ecommerce.showProducts.domain.models.Product;

public interface ProductService {

    List<Product> listAllProducts();
    
}
