package arquitecture.ecommerce.infrastructure.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import arquitecture.ecommerce.infrastructure.entities.CategoryEntity;
import arquitecture.ecommerce.infrastructure.entities.ProductEntity;
import arquitecture.ecommerce.infrastructure.entities.UserEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByNameContainingAndIsVisibleTrue(String keyword);
    List<ProductEntity> findTop6ByIsVisibleTrueOrderByReleaseDateDesc();
    List<ProductEntity> findByCategoryAndIsVisibleTrue(CategoryEntity category);
    List<ProductEntity> findByOwner(UserEntity owner);
    List<ProductEntity> findByIsVisibleTrue();

}
