package com.arquitecture.ecommerce.authentication.infrastructure.persistence;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface ClientEntityRepository extends JpaRepository<ClientEntity, Long> {
    Optional<ClientEntity> findByEmail(String email);
}
