package com.sahil.trackwallet.services;


import com.sahil.trackwallet.configs.security.JwtUtil;
import com.sahil.trackwallet.dtos.auth.AuthResponseDTO;
import com.sahil.trackwallet.dtos.auth.LoginRequestDTO;
import com.sahil.trackwallet.dtos.auth.RegisterRequestDTO;
import com.sahil.trackwallet.entity.User;
import com.sahil.trackwallet.enums.Role;
import com.sahil.trackwallet.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    @Override
    public AuthResponseDTO register(RegisterRequestDTO request) {

        if (userRepository.existsByEmail(request.getEmail())) {

            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        String token =
                jwtUtil.generateToken(user.getEmail(), user.getRole());

        return AuthResponseDTO.builder()
                .token(token)
                .message("User registered successfully")
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        String token =
                jwtUtil.generateToken(user.getEmail(), user.getRole());

        return AuthResponseDTO.builder()
                .token(token)
                .message("Login successful")
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
}