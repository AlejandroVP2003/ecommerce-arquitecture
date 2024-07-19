package arquitecture.ecommerce.domain.ports;

import arquitecture.ecommerce.domain.models.User;

public interface UserPersistance {
    
    void saveUser(User user);
    User getUserByEmail(String email);

}
