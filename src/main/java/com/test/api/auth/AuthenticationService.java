package com.test.api.auth;

import com.test.api.config.JwtService;
import com.test.api.model.Role;
import com.test.api.model.User;
import com.test.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {

        var user = User.builder()
                .first_name(request.getFirst_name())
                .last_name(request.getLast_name())
                .mobile_phone(request.getMobile_phone())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.user)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
            return  AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getMobile_phone(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getMobile_phone())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return  AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
