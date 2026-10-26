package com.example.demo.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import com.example.demo.entity.AppUser;
import com.example.demo.repository.AppUserRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
@RestController
public class AuthController {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AppUserRepository appUserRepository,
                          PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/api/auth/register")
    public ResponseEntity<AppUser> register(@RequestBody AppUser appUser) {

        // Convert plain-text password into BCrypt hash
        appUser.setPassword(
                passwordEncoder.encode(appUser.getPassword())
        );

        // Save user into database
        AppUser savedUser = appUserRepository.save(appUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
    
    @GetMapping("/api/secure/data")
    public String getSecureData() {
        return "Secure data accessed successfully!";
    }
    
    @PutMapping("/api/auth/profile")
    public ResponseEntity<AppUser> updateProfile(
            @RequestBody AppUser updatedUser,
            Authentication authentication) {

        String currentUsername = authentication.getName();

        AppUser existingUser = appUserRepository
                .findByUsername(currentUsername)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (updatedUser.getUsername() != null) {
            existingUser.setUsername(updatedUser.getUsername());
        }

        if (updatedUser.getRole() != null) {
            existingUser.setRole(updatedUser.getRole());
        }

        AppUser savedUser = appUserRepository.save(existingUser);

        return ResponseEntity.ok(savedUser);
    }
    
    //Authentication is provided by Spring Security:
    //"Spring Security, please give me the information about the user who is currently authenticated."
    @DeleteMapping("/api/auth/deregister")
    public ResponseEntity<Void> deregister(Authentication authentication) {

        String currentUsername = authentication.getName();

        AppUser existingUser = appUserRepository
                .findByUsername(currentUsername)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        appUserRepository.delete(existingUser);

        return ResponseEntity.noContent().build();
    }
    
    
}