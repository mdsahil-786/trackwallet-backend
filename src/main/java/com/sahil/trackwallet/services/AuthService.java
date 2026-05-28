package com.sahil.trackwallet.services;

import com.sahil.trackwallet.dto.auth.AuthResponseDTO;
import com.sahil.trackwallet.dto.auth.LoginRequestDTO;
import com.sahil.trackwallet.dto.auth.RegisterRequestDTO;

public interface AuthService {

    AuthResponseDTO register(RegisterRequestDTO request);

    AuthResponseDTO login(LoginRequestDTO request);
}