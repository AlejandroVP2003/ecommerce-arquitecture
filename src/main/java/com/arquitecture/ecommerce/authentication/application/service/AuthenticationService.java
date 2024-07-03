package com.arquitecture.ecommerce.authentication.application.service;
import com.arquitecture.ecommerce.authentication.application.dto.AuthenticationRequest;
import com.arquitecture.ecommerce.authentication.application.dto.AuthenticationResponse;
import com.arquitecture.ecommerce.authentication.application.dto.RegisterRequest;
public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
