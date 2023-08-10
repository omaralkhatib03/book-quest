package com.library.libraryapi.controllers;

import org.springframework.data.repository.support.Repositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.libraryapi.models.authentication.AuthenticationRequest;
import com.library.libraryapi.models.authentication.AuthenticationResponse;
import com.library.libraryapi.models.profile.ProfileResponse;
import com.library.libraryapi.models.register.RegisterRequest;
import com.library.libraryapi.models.register.RegisterResponse;
import com.library.libraryapi.services.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @RequestBody RegisterRequest req) {
        RegisterResponse res = authenticationService.register(req);
        if (res == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(res);
    }

    @GetMapping("/test")
    public String test() {
        return "Hello from unsecured endpoint!";
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest req) {
        AuthenticationResponse res = authenticationService.login(req);

        if (res == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        if (token == null || token.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        token = token.substring(7);
        String res = authenticationService.logout(token);
        return (res == null) ? ResponseEntity.badRequest().build() : ResponseEntity.ok(res);
    }

    @GetMapping()
    public ResponseEntity<ProfileResponse> getProfile(@RequestHeader("Authorization") String token) {
        System.out.println("Getting profile of: " + token);
        if (token == null || token.isEmpty() || token.equals("")) {
            return ResponseEntity.badRequest().build();
        }
        token = token.substring(7);
        
        ProfileResponse res = authenticationService.getProfile(token);
        return (res == null) ? ResponseEntity.badRequest().build() : ResponseEntity.ok(res);
    }


    @PutMapping()
    public ResponseEntity<ProfileResponse> updateProfile(@RequestBody RegisterRequest req) {        
        ProfileResponse res = authenticationService.updateProfile(req);
        return (res == null) ? ResponseEntity.badRequest().build() : ResponseEntity.ok(res);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteProfile(@RequestHeader("Authorization") String token) {
        if (token == null || token.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        token = token.substring(7);
        String res = authenticationService.deleteProfile(token);
        return (res == null) ? ResponseEntity.badRequest().build() : ResponseEntity.ok(res);
    }

}
