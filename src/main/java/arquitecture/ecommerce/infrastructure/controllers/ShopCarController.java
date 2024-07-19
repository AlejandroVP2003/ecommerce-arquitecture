package arquitecture.ecommerce.infrastructure.controllers;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import arquitecture.ecommerce.application.usecases.ProductService;
import arquitecture.ecommerce.application.usecases.ShopCarService;
import arquitecture.ecommerce.domain.models.Product;
import arquitecture.ecommerce.domain.models.ShopCar;
import arquitecture.ecommerce.domain.models.User;
import jakarta.servlet.http.HttpSession;


@Controller
public class ShopCarController {

    private final ShopCarService shopCarService;

    private final ProductService productService;

    public ShopCarController(ShopCarService shopCarService, ProductService productService) {
        this.shopCarService = shopCarService;
        this.productService = productService;
    }

    @GetMapping("/viewCar")
    public String viewShopCar(Model model, HttpSession session) {
        ShopCar shopCar = (ShopCar) session.getAttribute("shopCar");
        Map<Product, Integer> productsInCar = shopCarService.listProductsInShopCart(shopCar);
    
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry : productsInCar.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            total += (product.getPrice() * quantity);
        }

        model.addAttribute("productsInCar", productsInCar);
        model.addAttribute("total", total);

        return "shopCar";
    }

    @PostMapping("/addInCar")
    public String addProductInCar(@RequestParam Long productId, @RequestParam int quantity, HttpSession session) {
        User client = (User) session.getAttribute("user");
        if (client == null) {
            return "redirect:/loginView";
        }

        ShopCar shopCar = (ShopCar) session.getAttribute("shopCar");

        if (shopCar == null) {
            shopCar = shopCarService.listActiveShopCar(client);
            if (shopCar == null) {
                shopCar = new ShopCar();
                shopCar.setClient(client);
                shopCar = shopCarService.saveShopCar(shopCar);;
            }
            session.setAttribute("shopCar", shopCar);
        }

        Product selectedProduct = productService.listProduct(productId);
        shopCarService.addProductInShopCar(shopCar, selectedProduct, quantity);
        shopCarService.saveShopCar(shopCar);
        session.setAttribute("shopCar", shopCar);

        return "redirect:/buyProducts";
    }

    @PostMapping("/removeFromCar")
    public String removeProductInCar(@RequestParam Long productId, HttpSession session) {
        User client = (User) session.getAttribute("user");
        if (client == null) {
            return "redirect:/loginView";
        }
        Product product = productService.listProduct(productId);
        ShopCar shopCar = (ShopCar) session.getAttribute("shopCar");
        if (shopCar != null) {
            shopCarService.removeProductInShopCar(shopCar, product);
        }

        return "redirect:/buyProducts";
    }

    @PostMapping("/completePurchase")
    public String completePurchase(@RequestParam double total, HttpSession session) {
        User client = (User) session.getAttribute("user");
        if (client == null) {
            return "redirect:/loginView";
        }

        ShopCar shopCar = (ShopCar) session.getAttribute("shopCar");
        if (shopCar != null) {
            shopCarService.completePurchase(shopCar, total);
            session.removeAttribute("shopCar");
        }

        return "purchaseSuccess";
    }

    @GetMapping("/viewShopCars")
    public String viewCompletedShopCars(@RequestParam(required = false) Long selectedCarId,
                                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                        HttpSession session, Model model) {
        User client = (User) session.getAttribute("user");

        if (client == null) {
            return "redirect:/loginView";
        }

        List<ShopCar> completedShopCars;

        if (startDate != null && endDate != null) {
            completedShopCars = shopCarService.getCompletedShopCarsBetweenDates(client, startDate, endDate);
        } else {
            completedShopCars = shopCarService.getCompletedShopCars(client);
        }

        model.addAttribute("completedShopCars", completedShopCars);

        if (selectedCarId != null) {
            ShopCar shopCar = shopCarService.listSelectedShopCar(selectedCarId);
            if (shopCar != null) {
                Map<Product, Integer> productsInCar = shopCarService.listProductsInShopCart(shopCar);
                model.addAttribute("productsInCar", productsInCar);
            } else {
                model.addAttribute("productsInCar", Collections.emptyMap());
            }
        } else {
            model.addAttribute("productsInCar", Collections.emptyMap());
        }

        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        return "viewShopCars";
    }
}
