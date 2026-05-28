package com.sahil.trackwallet.services;

import com.sahil.trackwallet.dtos.auth.AuthResponseDTO;
import com.sahil.trackwallet.dtos.auth.LoginRequestDTO;
import com.sahil.trackwallet.dtos.auth.RegisterRequestDTO;

public interface AuthService {

    AuthResponseDTO register(RegisterRequestDTO request);

    AuthResponseDTO login(LoginRequestDTO request);
}