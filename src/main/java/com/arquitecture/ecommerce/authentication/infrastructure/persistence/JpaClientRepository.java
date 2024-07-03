package com.arquitecture.ecommerce.authentication.infrastructure.persistence;
import com.arquitecture.ecommerce.authentication.domain.model.Client;
import com.arquitecture.ecommerce.authentication.domain.repository.ClientRepository;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public class JpaClientRepository implements ClientRepository{
    @Autowired
    private ClientEntityRepository clientEntityRepository;

    @Override
    public Optional<Client> findByEmail(String email) {
        return clientEntityRepository.findByEmail(email).map(ClientMapper::toDomain);
    }

    @Override
    public void save(Client client) {
        clientEntityRepository.save(ClientMapper.toEntity(client));
    }
}
