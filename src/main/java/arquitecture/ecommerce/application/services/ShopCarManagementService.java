package arquitecture.ecommerce.application.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import arquitecture.ecommerce.application.usecases.ShopCarService;
import arquitecture.ecommerce.domain.models.Product;
import arquitecture.ecommerce.domain.models.ShopCar;
import arquitecture.ecommerce.domain.models.User;
import arquitecture.ecommerce.domain.ports.ShopCarPersistance;

@Service
public class ShopCarManagementService implements ShopCarService {

    private final ShopCarPersistance shopCarPersistance;

    public ShopCarManagementService(ShopCarPersistance shopCarPersistance) {
        this.shopCarPersistance = shopCarPersistance;
    }

    @Override
    public ShopCar listActiveShopCar(User client) {
        return shopCarPersistance.getActiveShopCar(client);
    }
    
    @Override
    public void addProductInShopCar(ShopCar shopCar, Product product, int quantity) {
        shopCarPersistance.saveProductInShopCar(shopCar, product, quantity);
    }

    @Override
    public Map<Product, Integer> listProductsInShopCart(ShopCar shopCar) {
        return shopCarPersistance.getProductsInShopCart(shopCar);
    }

    @Override
    public ShopCar saveShopCar(ShopCar shopCar) {
        return shopCarPersistance.saveShopCar(shopCar);
    }

    @Override
    public void deactivateShopCar(ShopCar shopCar) {
        shopCarPersistance.deactivateShopCar(shopCar);
    }

    @Override
    public void removeProductInShopCar(ShopCar shopCar, Product product) {
        shopCarPersistance.deleteProductInShopCar(shopCar, product);
    }

    @Override
    public void completePurchase(ShopCar shopCar, double total) {
        shopCarPersistance.completePurchase(shopCar, total);
    }

    @Override
    public List<ShopCar> getCompletedShopCars(User client) {
        return shopCarPersistance.getAllCompletedCars(client);
    }

    @Override
    public List<ShopCar> getCompletedShopCarsBetweenDates(User client, LocalDate startDate, LocalDate endDate) {
        return shopCarPersistance.getAllCompletedCarsBetweenDates(client, startDate, endDate);
    }

    @Override
    public ShopCar listSelectedShopCar(Long id) {
        return shopCarPersistance.getSelectedShopCar(id);
    }
    
}
