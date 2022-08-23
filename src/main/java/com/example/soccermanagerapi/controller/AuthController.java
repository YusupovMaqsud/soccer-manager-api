package com.example.soccermanagerapi.controller;

import com.example.soccermanagerapi.payload.ApiResponse;
import com.example.soccermanagerapi.payload.LoginDto;
import com.example.soccermanagerapi.payload.RegisterDto;
import com.example.soccermanagerapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;


    @PostMapping("/register")
    public HttpEntity<?> registerUser(@RequestBody RegisterDto registerDto){
        ApiResponse apiResponse = authService.registerUser(registerDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    @GetMapping("/verifyEmail")
    public HttpEntity<?> verifyEmail(@RequestParam String emailCode, @RequestParam String email) {
        ApiResponse response = authService.verifyEmail(emailCode, email);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto loginDto) {
        ApiResponse response = authService.login(loginDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }
}
