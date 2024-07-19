package arquitecture.ecommerce.infrastructure.mappers;

import java.util.stream.Collectors;

import arquitecture.ecommerce.domain.models.User;
import arquitecture.ecommerce.infrastructure.entities.UserEntity;

public class UserMapper {
    
    public static User toModel(UserEntity entity, boolean includeProducts) {
        if (entity == null) {
            return null;
        }

        User user = new User();
        user.setId(entity.getId());
        user.setName(entity.getName());
        user.setLastName(entity.getLastName());
        user.setPhoneNumber(entity.getPhoneNumber());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());

        if (includeProducts) {
            user.setProducts(entity.getProducts().stream().map(product -> ProductMapper.toDomain(product, false)).collect(Collectors.toSet()));
        }

        return user;
    }

    public static UserEntity toEntity(User model, boolean includeProducts) {
        if (model == null) {
            return null;
        }

        UserEntity entity = new UserEntity();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setLastName(model.getLastName());
        entity.setPhoneNumber(model.getPhoneNumber());
        entity.setEmail(model.getEmail());
        entity.setPassword(model.getPassword());

        if (includeProducts) {
            entity.setProducts(model.getProducts().stream().map(product -> ProductMapper.toEntity(product, false)).collect(Collectors.toSet()));
        }

        return entity;
    }
}
