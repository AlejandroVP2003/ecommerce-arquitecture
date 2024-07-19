package arquitecture.ecommerce.infrastructure.controllers;

import java.util.Map;

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
            return "redirect:/login";
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
            return "redirect:/login";
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
            return "redirect:/login";
        }

        ShopCar shopCar = (ShopCar) session.getAttribute("shopCar");
        if (shopCar != null) {
            shopCarService.completePurchase(shopCar, total);
            session.removeAttribute("shopCar");
        }

        return "purchaseSuccess";
    }
}
