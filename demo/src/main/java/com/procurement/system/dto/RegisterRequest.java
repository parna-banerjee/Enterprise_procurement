package com.procurement.system.dto;

import com.procurement.system.enums.UserRole;
import lombok.Data;

@Data
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private UserRole phone;

}