package com.procurement.system.service;

import com.procurement.system.dto.LoginRequest;
import com.procurement.system.dto.LoginResponse;

public interface AdminService {

    LoginResponse login(LoginRequest request);

}