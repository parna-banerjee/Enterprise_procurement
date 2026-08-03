package com.procurement.system.controller;

import com.procurement.system.dto.LoginRequest;
import com.procurement.system.dto.LoginResponse;
import com.procurement.system.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        return adminService.login(request);

    }
}