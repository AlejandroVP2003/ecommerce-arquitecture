package com.arquitecture.ecommerce.authentication.infrastructure.persistence;
import com.arquitecture.ecommerce.authentication.domain.model.Client;
import com.arquitecture.ecommerce.authentication.domain.model.PersonalClient;
import com.arquitecture.ecommerce.authentication.domain.model.BusinessClient;

public class ClientMapper {
    public static ClientEntity toEntity(Client client) {
        if (client instanceof PersonalClient) {
            PersonalClient personalClient = (PersonalClient) client;
            PersonalClientEntity entity = new PersonalClientEntity();
            entity.setEmail(personalClient.getEmail());
            entity.setPassword(personalClient.getPassword());
            entity.setFirstName(personalClient.getFirstName());
            entity.setLastName(personalClient.getLastName());
            entity.setPhoneNumber(personalClient.getPhoneNumber());
            return entity;
        } else if (client instanceof BusinessClient) {
            BusinessClient businessClient = (BusinessClient) client;
            BusinessClientEntity entity = new BusinessClientEntity();
            entity.setEmail(businessClient.getEmail());
            entity.setPassword(businessClient.getPassword());
            entity.setRuc(businessClient.getRuc());
            entity.setPhoneNumber(businessClient.getPhoneNumber());
            return entity;
        }
        throw new IllegalArgumentException("Unknown client type");
    }

    public static Client toDomain(ClientEntity entity) {
        if (entity instanceof PersonalClientEntity) {
            PersonalClientEntity personalClientEntity = (PersonalClientEntity) entity;
            PersonalClient personalClient = new PersonalClient();
            personalClient.setEmail(personalClientEntity.getEmail());
            personalClient.setPassword(personalClientEntity.getPassword());
            personalClient.setFirstName(personalClientEntity.getFirstName());
            personalClient.setLastName(personalClientEntity.getLastName());
            personalClient.setPhoneNumber(personalClientEntity.getPhoneNumber());
            return personalClient;
        } else if (entity instanceof BusinessClientEntity) {
            BusinessClientEntity businessClientEntity = (BusinessClientEntity) entity;
            BusinessClient businessClient = new BusinessClient();
            businessClient.setEmail(businessClientEntity.getEmail());
            businessClient.setPassword(businessClientEntity.getPassword());
            businessClient.setRuc(businessClientEntity.getRuc());
            businessClient.setPhoneNumber(businessClientEntity.getPhoneNumber());
            return businessClient;
        }
        throw new IllegalArgumentException("Unknown client entity type");
    }
}
