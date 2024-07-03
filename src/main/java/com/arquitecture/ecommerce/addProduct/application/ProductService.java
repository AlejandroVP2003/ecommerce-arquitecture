package com.arquitecture.ecommerce.addProduct.application;

import com.arquitecture.ecommerce.addProduct.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();
    Product getProductById(Long id);
    Product addProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}
