package arquitecture.ecommerce.application.usecases;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import arquitecture.ecommerce.domain.models.Product;
import arquitecture.ecommerce.domain.models.ShopCar;
import arquitecture.ecommerce.domain.models.User;

public interface ShopCarService {

    ShopCar listActiveShopCar(User client);
    void addProductInShopCar(ShopCar shopCar, Product product, int quantity);
    
    Map<Product, Integer> listProductsInShopCart(ShopCar shopCar);
    ShopCar saveShopCar(ShopCar shopCar);

    void deactivateShopCar(ShopCar shopCar);
    void removeProductInShopCar(ShopCar shopCar, Product product);
    void completePurchase(ShopCar shopCar, double total);

    List<ShopCar> getCompletedShopCars(User client);
    List<ShopCar> getCompletedShopCarsBetweenDates(User client, LocalDate startDate, LocalDate endDate);
    ShopCar listSelectedShopCar(Long id);

} 
