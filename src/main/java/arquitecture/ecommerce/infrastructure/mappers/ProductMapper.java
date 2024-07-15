package arquitecture.ecommerce.infrastructure.mappers;

import arquitecture.ecommerce.domain.models.Product;
import arquitecture.ecommerce.infrastructure.entities.ProductEntity;

public class ProductMapper {

    public static Product toDomain(ProductEntity entity) {
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
        product.setUpdateDate(entity.getUpdateDate());
        product.setBrand(entity.getBrand());
        product.setWeight(entity.getWeight());
        product.setLength(entity.getLength());
        product.setWidth(entity.getWidth());
        product.setHeight(entity.getHeight());
        product.setProductCondition(entity.getProductCondition());
        product.setRating(entity.getRating());
        product.setVisible(entity.isVisible());
        product.setImagesPath(entity.getImagesPath());

        if (entity.getCategoryEntity() != null) {
            product.setCategory(CategoryMapper.toDomain(entity.getCategoryEntity()));
        }

        return product;
    }

    public static ProductEntity toEntity(Product domain) {
        if (domain == null) {
            return null;
        }

        ProductEntity entity = new ProductEntity();
        entity.setId(domain.getId());
        entity.setSku(domain.getSku());
        entity.setName(domain.getName());
        entity.setDescription(domain.getDescription());
        entity.setStock(domain.getStock());
        entity.setPrice(domain.getPrice());
        entity.setReleaseDate(domain.getReleaseDate());
        entity.setUpdateDate(domain.getUpdateDate());
        entity.setBrand(domain.getBrand());
        entity.setWeight(domain.getWeight());
        entity.setLength(domain.getLength());
        entity.setWidth(domain.getWidth());
        entity.setHeight(domain.getHeight());
        entity.setProductCondition(domain.getProductCondition());
        entity.setRating(domain.getRating());
        entity.setVisible(domain.isVisible());
        entity.setImagesPath(domain.getImagesPath());

        if (domain.getCategory() != null) {
            entity.setCategoryEntity(CategoryMapper.toEntity(domain.getCategory()));
        }

        return entity;
    }

}
