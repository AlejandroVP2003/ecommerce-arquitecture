package arquitecture.ecommerce.infrastructure.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import arquitecture.ecommerce.application.usecases.CategoryService;
import arquitecture.ecommerce.application.usecases.ProductService;
import arquitecture.ecommerce.domain.models.Category;
import arquitecture.ecommerce.domain.models.Product;

@Controller
public class ProductController {
    
    private final ProductService productService;

    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/buyProducts")
    public String listProducts(Model model, @RequestParam(required = false) String keyword) {
        List<Product> products;
        if (keyword != null) {
            products = productService.findByName(keyword);
        } else {
            products = productService.listAllProducts();
        }
        List<Category> mainCategories = categoryService.listMainCategories();
        model.addAttribute("mainCategories", mainCategories);
        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);
        return "buyProducts";
    }

    @GetMapping("/viewProduct")
    public String viewProductSelected(@RequestParam Long id, Model model) {
        List<Category> mainCategories = categoryService.listMainCategories();
        Product product = productService.listProduct(id);
        model.addAttribute("mainCategories", mainCategories);
        model.addAttribute("product", product);
        return "viewProduct";
    }

    @GetMapping("/")
    public String welcomePage(Model model) {
        List<Category> categories = categoryService.listAllCategories();
        List<Product> lastProducts = productService.listLastSixProducts();
        model.addAttribute("categories", categories);
        model.addAttribute("lastProducts", lastProducts);
        return "index";
    }

    @GetMapping("/filterProducts")
    public String viewProductsByCategory(@RequestParam Long categoryId, Model model) {
        Category selectedCategory = new Category();
        selectedCategory.setId(categoryId);
        List<Category> mainCategories = categoryService.listMainCategories();
        List<Product> products = productService.listProductsByCategory(selectedCategory);
        model.addAttribute("mainCategories", mainCategories);
        model.addAttribute("products", products);
        return "buyProducts";
    }

}
