package com.arquitecture.ecommerce.authentication.application.service;

import com.arquitecture.ecommerce.authentication.application.dto.AuthenticationRequest;
import com.arquitecture.ecommerce.authentication.application.dto.AuthenticationResponse;
import com.arquitecture.ecommerce.authentication.application.dto.RegisterRequest;
import com.arquitecture.ecommerce.authentication.domain.model.Client;
import com.arquitecture.ecommerce.authentication.domain.repository.ClientRepository;
import com.arquitecture.ecommerce.authentication.infrastructure.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        Client client = new Client();
        client.setEmail(request.getEmail());
        client.setPassword(passwordEncoder.encode(request.getPassword()));
        // Add additional fields based on request type (Personal/Business)
        clientRepository.save(client);

        String jwt = jwtUtils.generateToken(client);

        return new AuthenticationResponse(jwt);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Client client = clientRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), client.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String jwt = jwtUtils.generateToken(client);

        return new AuthenticationResponse(jwt);
    }

}
