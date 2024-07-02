package com.arquitecture.ecommerce.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arquitecture.ecommerce.application.usecases.ProductService;
import com.arquitecture.ecommerce.domain.models.Product;
import com.arquitecture.ecommerce.domain.ports.ProductPersistance;

@Service
public class ProductManagementService implements ProductService {

    @Autowired
    private ProductPersistance productPersistance;

    @Override
    public List<Product> listAllProducts() {
        return productPersistance.getAllProducts();
    }

}
