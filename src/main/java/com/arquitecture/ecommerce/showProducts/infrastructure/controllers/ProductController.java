package com.arquitecture.ecommerce.infrastructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arquitecture.ecommerce.application.usecases.CategoryService;
import com.arquitecture.ecommerce.application.usecases.ProductService;
import com.arquitecture.ecommerce.domain.models.Category;
import com.arquitecture.ecommerce.domain.models.Product;

@Controller
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String listCategories(Model model) {
        List<Category> mainCategories = categoryService.listMainCategories();
        List<Category> subCategories = categoryService.listAllCategories();
        List<Product> products = productService.listAllProducts();
        model.addAttribute("mainCategories", mainCategories);
        model.addAttribute("subCategories", subCategories);
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/product")
    public String viewProduct(Model model) {
        List<Category> mainCategories = categoryService.listMainCategories();
        List<Category> subCategories = categoryService.listAllCategories();
        model.addAttribute("mainCategories", mainCategories);
        model.addAttribute("subCategories", subCategories);
        return "viewProduct";
    }

}