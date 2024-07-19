package arquitecture.ecommerce.application.services;

import org.springframework.stereotype.Service;

import arquitecture.ecommerce.application.usecases.UserService;
import arquitecture.ecommerce.domain.models.User;
import arquitecture.ecommerce.domain.ports.UserPersistance;

@Service
public class UserManagementService implements UserService {

    private final UserPersistance userPersistance;

    public UserManagementService(UserPersistance userPersistance) {
        this.userPersistance = userPersistance;
    }

    @Override
    public void addUser(User user) {
        userPersistance.saveUser(user);
    }

    @Override
    public User loginUser(String email, String inputPassword) {
        return userPersistance.getUserByEmail(email, inputPassword);
    }
    
}
