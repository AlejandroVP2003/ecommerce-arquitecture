package arquitecture.ecommerce.application.usecases;

import arquitecture.ecommerce.domain.models.User;

public interface UserService {
    
    void addUser(User user);
    User loginUser(String email, String inputPassword);

}
