package arquitecture.ecommerce.infrastructure.controllers;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import arquitecture.ecommerce.application.usecases.CategoryService;
import arquitecture.ecommerce.application.usecases.ImageUploadService;
import arquitecture.ecommerce.application.usecases.ProductService;
import arquitecture.ecommerce.domain.models.Category;
import arquitecture.ecommerce.domain.models.Product;
import arquitecture.ecommerce.domain.models.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {
    
    private final ProductService productService;

    private final CategoryService categoryService;

    private final ImageUploadService imageUploadService;

    public ProductController(ProductService productService, CategoryService categoryService, ImageUploadService imageUploadService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.imageUploadService = imageUploadService;
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

    @GetMapping(value = "/uploads/{filename}")
	public ResponseEntity<Resource> goImage(@PathVariable String filename) {
		Resource resource = null;
		try {
			resource = imageUploadService.loadImage(filename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
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

    @GetMapping("/sellProduct")
    public String viewProductSellForm(Model model) {
        List<Category> categories = categoryService.listAllCategories();
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categories);
        return "sellProduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute Product product, @RequestParam("categoryId") String categoryId, @RequestParam("images") List<MultipartFile> files, HttpSession session) {
        User owner = (User) session.getAttribute("user");
        if (owner == null) {
            return "redirect:/login";
        }

        Category category = categoryService.listCategoryById(Long.parseLong(categoryId));

        List<String> imagePaths = new ArrayList<>();
        for (MultipartFile file : files) {
            String filePath = imageUploadService.saveImage(file);
            imagePaths.add(filePath);
        }

        product.setOwner(owner);
        product.setCategory(category);
        product.setImagesPath(imagePaths);
        productService.addProduct(product);

        return "redirect:/buyProducts";
    }

}
