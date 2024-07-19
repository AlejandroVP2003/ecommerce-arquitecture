package arquitecture.ecommerce.infrastructure.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import arquitecture.ecommerce.infrastructure.entities.ShopCarEntity;
import arquitecture.ecommerce.infrastructure.entities.UserEntity;

public interface ShopCarRepository extends JpaRepository <ShopCarEntity, Long> {

    ShopCarEntity findByClientIdAndIsActiveTrue(Long userId);
    List<ShopCarEntity> findByClientAndIsCompletedTrue(UserEntity client);
    List<ShopCarEntity> findByClientAndIsCompletedTrueAndCreationDateBetween(UserEntity client, LocalDate startDate, LocalDate endDate);

}
