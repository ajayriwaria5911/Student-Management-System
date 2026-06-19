package com.example.backend.controller;

import com.example.backend.dto.LoginRequestDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO dto) {
    
	   if(dto.getUsername().equals("admin") && dto.getPassword().equals("admin"))         {
            return "JWT_TOKEN";
        }

        return "Invalid Credentials";
    }
}