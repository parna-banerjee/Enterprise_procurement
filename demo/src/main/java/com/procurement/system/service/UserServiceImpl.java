package com.procurement.system.service;

import com.procurement.system.dto.LoginRequest;
import com.procurement.system.dto.LoginResponse;
import com.procurement.system.dto.RegisterRequest;
import com.procurement.system.entity.User;
import com.procurement.system.enums.UserRole;
import com.procurement.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already exists";
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());
        user.setRole(UserRole.EMPLOYEE);

        userRepository.save(user);

        return "User Registered Successfully";
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmailAndPassword(
                request.getEmail(),
                request.getPassword()
        ).orElse(null);

        if (user == null) {
            return new LoginResponse("Invalid Email or Password", null);
        }

        return new LoginResponse("Login Successful", user.getRole().name());
    }
}