package com.procurement.system.service;

import com.procurement.system.dto.LoginRequest;
import com.procurement.system.dto.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private static final String ADMIN_EMAIL = "admin@procurement.com";
    private static final String ADMIN_PASSWORD = "admin123";

    @Override
    public LoginResponse login(LoginRequest request) {

        if (ADMIN_EMAIL.equals(request.getEmail())
                && ADMIN_PASSWORD.equals(request.getPassword())) {

            return new LoginResponse(
                    "Login Successful",
                    "ADMIN"
            );
        }

        return new LoginResponse(
                "Invalid Admin Credentials",
                null
        );
    }
}