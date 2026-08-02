package com.procurement.system.service;

import com.procurement.system.dto.LoginRequest;
import com.procurement.system.dto.LoginResponse;
import com.procurement.system.dto.RegisterRequest;

public interface UserService {

    String register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}