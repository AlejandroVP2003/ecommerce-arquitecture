package com.arquitecture.ecommerce.authentication.domain.repository;

import com.arquitecture.ecommerce.authentication.domain.model.Client;
import java.util.Optional;

public interface ClientRepository {
    Optional<Client> findByEmail(String email);
    void save(Client client);


}
