package com.arquitecture.ecommerce.addProduct.infrastructure;

import com.arquitecture.ecommerce.addProduct.domain.Category;
import com.arquitecture.ecommerce.addProduct.domain.User;
import org.springframework.ui.Model;
import com.arquitecture.ecommerce.addProduct.application.ProductService;
import com.arquitecture.ecommerce.addProduct.application.UploadFileService;
import com.arquitecture.ecommerce.addProduct.application.UserService;
import com.arquitecture.ecommerce.addProduct.application.CategoryService;
import com.arquitecture.ecommerce.addProduct.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private UploadFileService uploadFileService;


    @GetMapping
    public String showWelcome() {
        return "product";
    }

    @GetMapping("/new")
    public String showAddProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getCategorys());
        model.addAttribute("accion", "/product/new");
        return "addProduct";

    }

    @PostMapping("/new")
    public String addProduct(@Validated @ModelAttribute("product") Product product,
                             BindingResult result, Model model,
                             @RequestParam("file") MultipartFile file,
                             RedirectAttributes redirectAttributes,
                             SessionStatus status) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("categories", categoryService.getCategorys()); //
                return "product/productForm";
            }


            long idUser = 1;
            User user = userService.getUserById(idUser);
            product.setUser(user);


            Category category = categoryService.getCategoryById(product.getCategory().getCategoryId());
            product.setCategory(category);


            if (!file.isEmpty()) {
                String fileName = uploadFileService.copy(file);
                product.setImage(fileName);
            }

            // Guardar producto
            productService.addProduct(product);
            status.setComplete();
            return "redirect:/product";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error adding product: " + e.getMessage());
            return "redirect:/product/new";
        }

    }
}
