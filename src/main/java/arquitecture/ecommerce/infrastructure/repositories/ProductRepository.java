package arquitecture.ecommerce.infrastructure.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import arquitecture.ecommerce.infrastructure.entities.CategoryEntity;
import arquitecture.ecommerce.infrastructure.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByNameContaining(String keyword);
    List<ProductEntity> findTop6ByOrderByReleaseDateDesc();
    List<ProductEntity> findByCategoryEntity(CategoryEntity categoryEntity);

}
