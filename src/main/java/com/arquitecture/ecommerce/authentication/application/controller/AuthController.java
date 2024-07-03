package com.arquitecture.ecommerce.authentication.application.controller;
import com.arquitecture.ecommerce.authentication.application.dto.AuthenticationRequest;
import com.arquitecture.ecommerce.authentication.application.dto.AuthenticationResponse;
import com.arquitecture.ecommerce.authentication.application.dto.RegisterRequest;
import com.arquitecture.ecommerce.authentication.application.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public AuthenticationResponse register(@RequestBody RegisterRequest request) {
        return authenticationService.register(request);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest request) {
        return authenticationService.authenticate(request);
    }
}
