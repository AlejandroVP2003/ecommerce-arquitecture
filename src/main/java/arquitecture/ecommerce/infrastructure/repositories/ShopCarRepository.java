package arquitecture.ecommerce.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import arquitecture.ecommerce.infrastructure.entities.ShopCarEntity;

public interface ShopCarRepository extends JpaRepository <ShopCarEntity, Long> {

    ShopCarEntity findByClientIdAndIsActiveTrue(Long userId);

}
