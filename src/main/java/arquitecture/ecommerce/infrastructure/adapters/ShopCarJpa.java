package arquitecture.ecommerce.infrastructure.adapters;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import arquitecture.ecommerce.domain.models.Product;
import arquitecture.ecommerce.domain.models.ShopCar;
import arquitecture.ecommerce.domain.models.User;
import arquitecture.ecommerce.domain.ports.ShopCarPersistance;
import arquitecture.ecommerce.infrastructure.entities.ProductEntity;
import arquitecture.ecommerce.infrastructure.entities.ShopCarEntity;
import arquitecture.ecommerce.infrastructure.mappers.ProductMapper;
import arquitecture.ecommerce.infrastructure.mappers.ShopCarMapper;
import arquitecture.ecommerce.infrastructure.mappers.UserMapper;
import arquitecture.ecommerce.infrastructure.repositories.ProductRepository;
import arquitecture.ecommerce.infrastructure.repositories.ShopCarRepository;

@Repository
public class ShopCarJpa implements ShopCarPersistance {

    private final ShopCarRepository shopCarRepository;

    private final ProductRepository productRepository;

    public ShopCarJpa(ShopCarRepository shopCarRepository, ProductRepository productRepository) {
        this.shopCarRepository = shopCarRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ShopCar getActiveShopCar(User client) {
        ShopCarEntity shopCarEntity = shopCarRepository.findByClientIdAndIsActiveTrue(client.getId());
        return ShopCarMapper.toDomain(shopCarEntity);
    }

    @Override
    public void saveProductInShopCar(ShopCar shopCar, Product product, int quantity) {
        Map<Long, Integer> products = shopCar.getProducts();
        Long productId = product.getId();
        if (products.containsKey(productId)) {
            int existingQuantity = products.get(productId);
            if ((existingQuantity + quantity) < product.getStock()) {
                products.put(productId, existingQuantity + quantity);
            } else {
                products.put(productId, quantity);
            }
        } else {
            products.put(productId, quantity);
        }
        shopCar.setProducts(products);

        shopCarRepository.save(ShopCarMapper.toEntity(shopCar));
    }

    @Override
    public Map<Product, Integer> getProductsInShopCart(ShopCar shopCar) {
        if (shopCar == null) {
            return new HashMap<>();
        }
        Map<Product, Integer> productsWithQuantity = new HashMap<>();
        for (Map.Entry<Long, Integer> entry : shopCar.getProducts().entrySet()) {
            ProductEntity productEntity = productRepository.findById(entry.getKey()).orElse(null);
            Product product = ProductMapper.toDomain(productEntity, false);
            if (product != null) {
                productsWithQuantity.put(product, entry.getValue());
            }
        }
        return productsWithQuantity;
    }

    @Override
    public ShopCar saveShopCar(ShopCar shopCar) {
        ShopCarEntity shopCarEntity = shopCarRepository.save(ShopCarMapper.toEntity(shopCar));
        return ShopCarMapper.toDomain(shopCarEntity);
    }

    @Override
    public void deactivateShopCar(ShopCar shopCar) {
        shopCar.setActive(false);
        ShopCarEntity shopCarEntity = ShopCarMapper.toEntity(shopCar);
        shopCarRepository.save(shopCarEntity);
    }

    @Override
    public void deleteProductInShopCar(ShopCar shopCar, Product product) {
        Map<Long, Integer> products = shopCar.getProducts();
        products.remove(product.getId());
        shopCar.setProducts(products);
        shopCarRepository.save(ShopCarMapper.toEntity(shopCar));
    }

    @Override
    public void completePurchase(ShopCar shopCar, double total) {
        shopCar.setActive(false);
        shopCar.setCompleted(true);
        shopCar.setTotal(total);
        for (Map.Entry<Long, Integer> entry : shopCar.getProducts().entrySet()) {
            ProductEntity productEntity = productRepository.findById(entry.getKey()).orElseThrow();
            int newStock = productEntity.getStock() - entry.getValue();
            productEntity.setStock(newStock);
            productRepository.save(productEntity);
        }
        ShopCarEntity shopCarEntity = ShopCarMapper.toEntity(shopCar);
        shopCarRepository.save(shopCarEntity);
    }

    @Override
    public List<ShopCar> getAllCompletedCars(User client) {
        List<ShopCarEntity> shopCarEntities = shopCarRepository.findByClientAndIsCompletedTrue(UserMapper.toEntity(client, false));
        List<ShopCar> shopCars = new ArrayList<>();
        for (ShopCarEntity entity : shopCarEntities) {
            shopCars.add(ShopCarMapper.toDomain(entity));
        }
        return shopCars;
    }

    @Override
    public List<ShopCar> getAllCompletedCarsBetweenDates(User client, LocalDate startDate, LocalDate endDate) {
        List<ShopCarEntity> shopCarEntities = shopCarRepository.findByClientAndIsCompletedTrueAndCreationDateBetween(UserMapper.toEntity(client, false), startDate, endDate);
        List<ShopCar> shopCars = new ArrayList<>();
        for (ShopCarEntity entity : shopCarEntities) {
            shopCars.add(ShopCarMapper.toDomain(entity));
        }
        return shopCars;
    }

    @Override
    public ShopCar getSelectedShopCar(Long id) {
        ShopCarEntity shopCarEntity = shopCarRepository.findById(id).orElseThrow();
        return ShopCarMapper.toDomain(shopCarEntity);
    }

}
