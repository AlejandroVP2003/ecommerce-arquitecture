package arquitecture.ecommerce.infrastructure.adapters;

import java.util.NoSuchElementException;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import arquitecture.ecommerce.domain.models.User;
import arquitecture.ecommerce.domain.ports.UserPersistance;
import arquitecture.ecommerce.infrastructure.entities.UserEntity;
import arquitecture.ecommerce.infrastructure.mappers.UserMapper;
import arquitecture.ecommerce.infrastructure.repositories.UserRepository;
import arquitecture.ecommerce.infrastructure.security.PasswordUtils;

@Repository
public class UserJpa implements UserPersistance {

    private final UserRepository userRepository;

    public UserJpa(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) throws DuplicateKeyException {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateKeyException("Correo ya existente.");
        }
        String hashedPassword = PasswordUtils.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(UserMapper.toEntity(user, false));
    }

    @Override
    public User getUserByEmail(String email, String inputPassword) throws NoSuchElementException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new NoSuchElementException("No hay usuario con el email: " + email);
        }

        if (PasswordUtils.checkPassword(inputPassword, userEntity.getPassword())) {
            return UserMapper.toModel(userEntity, true);
        } else {
            throw new IllegalArgumentException("Contraseña incorrecta.");
        } 
    }
    
}
