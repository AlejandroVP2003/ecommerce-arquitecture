package arquitecture.ecommerce.infrastructure.mappers;

import java.util.HashMap;
import java.util.Map;

import arquitecture.ecommerce.domain.models.ShopCar;
import arquitecture.ecommerce.domain.models.User;
import arquitecture.ecommerce.infrastructure.entities.ShopCarEntity;
import arquitecture.ecommerce.infrastructure.entities.UserEntity;

public class ShopCarMapper {
    
    public static ShopCar toDomain(ShopCarEntity entity) {
        if (entity == null) {
            return null;
        }

        ShopCar model = new ShopCar();
        User user = UserMapper.toModel(entity.getClient(), false);
        Map<Long, Integer> products = new HashMap<>(entity.getProducts());
        model.setId(entity.getId());
        model.setClient(user);
        model.setProducts(products);
        model.setCreationDate(entity.getCreationDate());
        model.setActive(entity.isActive());
        model.setCompleted(entity.isCompleted());
        model.setTotal(entity.getTotal());

        return model;
    }

    public static ShopCarEntity toEntity(ShopCar model) {
        if (model == null) {
            return null;
        }

        ShopCarEntity entity = new ShopCarEntity();
        UserEntity userEntity = UserMapper.toEntity(model.getClient(), false);
        Map<Long, Integer> productsEntity = new HashMap<>(model.getProducts());
        entity.setId(model.getId());
        entity.setClient(userEntity);
        entity.setProducts(productsEntity);
        entity.setCreationDate(model.getCreationDate());
        entity.setActive(model.isActive());
        entity.setCompleted(model.isCompleted());
        entity.setTotal(model.getTotal());

        return entity;
    }

}
