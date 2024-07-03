package com.arquitecture.ecommerce.addProduct.application;


import com.arquitecture.ecommerce.addProduct.domain.Category;
import com.arquitecture.ecommerce.addProduct.domain.Product;
import com.arquitecture.ecommerce.addProduct.infrastructure.CategoryRepository;
import com.arquitecture.ecommerce.addProduct.infrastructure.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product productSearch = productRepository.findById(id).orElse(null);
        long categoryId = product.getCategory().getCategoryId();

        Category category= categoryRepository.findById(categoryId).orElse(null);
        if(productSearch != null) {
            productSearch.setName(productSearch.getName());
            productSearch.setDescription(productSearch.getDescription());
            productSearch.setPrice(productSearch.getPrice());
            productSearch.setCategory(category);
            productRepository.save(productSearch);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);

    }
}
