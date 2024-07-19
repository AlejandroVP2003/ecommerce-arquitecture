package arquitecture.ecommerce.infrastructure.mappers;

import arquitecture.ecommerce.domain.models.Product;
import arquitecture.ecommerce.infrastructure.entities.ProductEntity;

public class ProductMapper {

    public static Product toDomain(ProductEntity entity, boolean includeOwner) {
        if (entity == null) {
            return null;
        }

        Product product = new Product();
        product.setId(entity.getId());
        product.setSku(entity.getSku());
        product.setName(entity.getName());
        product.setDescription(entity.getDescription());
        product.setStock(entity.getStock());
        product.setPrice(entity.getPrice());
        product.setReleaseDate(entity.getReleaseDate());
        product.setBrand(entity.getBrand());
        product.setWeight(entity.getWeight());
        product.setLength(entity.getLength());
        product.setWidth(entity.getWidth());
        product.setHeight(entity.getHeight());
        product.setProductCondition(entity.getProductCondition());
        product.setRating(entity.getRating());
        product.setVisible(entity.isVisible());
        product.setImagesPath(entity.getImagesPath());

        if (entity.getCategory() != null) {
            product.setCategory(CategoryMapper.toModel(entity.getCategory()));
        }

        if (includeOwner && entity.getOwner() != null) {
            product.setOwner(UserMapper.toModel(entity.getOwner(), false));
        }

        return product;
    }

    public static ProductEntity toEntity(Product model, boolean includeOwner) {
        if (model == null) {
            return null;
        }

        ProductEntity entity = new ProductEntity();
        entity.setId(model.getId());
        entity.setSku(model.getSku());
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.setStock(model.getStock());
        entity.setPrice(model.getPrice());
        entity.setReleaseDate(model.getReleaseDate());
        entity.setBrand(model.getBrand());
        entity.setWeight(model.getWeight());
        entity.setLength(model.getLength());
        entity.setWidth(model.getWidth());
        entity.setHeight(model.getHeight());
        entity.setProductCondition(model.getProductCondition());
        entity.setRating(model.getRating());
        entity.setVisible(model.isVisible());
        entity.setImagesPath(model.getImagesPath());

        if (model.getCategory() != null) {
            entity.setCategory(CategoryMapper.toEntity(model.getCategory()));
        }

        if (includeOwner && model.getOwner() != null) {
            entity.setOwner(UserMapper.toEntity(model.getOwner(), false));
        }

        return entity;
    }
}
