package arquitecture.ecommerce.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import arquitecture.ecommerce.infrastructure.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
    boolean existsByEmail(String email);
    UserEntity findByEmail(String email);

}
