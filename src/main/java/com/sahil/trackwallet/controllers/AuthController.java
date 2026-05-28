package com.sahil.trackwallet.controllers;

import com.sahil.trackwallet.dtos.auth.AuthResponseDTO;
import com.sahil.trackwallet.dtos.auth.LoginRequestDTO;
import com.sahil.trackwallet.dtos.auth.RegisterRequestDTO;
import com.sahil.trackwallet.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(
            @Valid @RequestBody RegisterRequestDTO request
    ) {

        return new ResponseEntity<>(
                authService.register(request),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO request
    ) {

        return ResponseEntity.ok(
                authService.login(request)
        );
    }
}
