package arquitecture.ecommerce.domain.ports;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import arquitecture.ecommerce.domain.models.Product;
import arquitecture.ecommerce.domain.models.ShopCar;
import arquitecture.ecommerce.domain.models.User;

public interface ShopCarPersistance {
    
    ShopCar getActiveShopCar(User client);
    void saveProductInShopCar(ShopCar shopCar, Product product, int quantity);

    Map<Product, Integer> getProductsInShopCart(ShopCar shopCar);
    ShopCar saveShopCar(ShopCar shopCar);

    void deactivateShopCar(ShopCar shopCar);
    void deleteProductInShopCar(ShopCar shopCar, Product product);
    void completePurchase(ShopCar shopCar, double total);

    List<ShopCar> getAllCompletedCars(User client);
    List<ShopCar> getAllCompletedCarsBetweenDates(User client, LocalDate startDate, LocalDate endDate);
    ShopCar getSelectedShopCar(Long id);

}
