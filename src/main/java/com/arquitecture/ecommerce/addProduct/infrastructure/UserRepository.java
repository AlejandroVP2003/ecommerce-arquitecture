package com.arquitecture.ecommerce.addProduct.infrastructure;

import com.arquitecture.ecommerce.addProduct.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
