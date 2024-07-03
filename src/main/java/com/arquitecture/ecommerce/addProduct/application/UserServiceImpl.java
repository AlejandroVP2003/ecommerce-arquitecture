package com.arquitecture.ecommerce.addProduct.application;

import com.arquitecture.ecommerce.addProduct.domain.User;
import com.arquitecture.ecommerce.addProduct.infrastructure.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
