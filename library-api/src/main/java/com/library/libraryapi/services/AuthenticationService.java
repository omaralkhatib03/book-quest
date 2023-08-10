package com.library.libraryapi.services;

import java.util.List;
import java.util.Optional;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.library.libraryapi.auth.AuthProvider;
import com.library.libraryapi.models.authentication.AuthenticationRequest;
import com.library.libraryapi.models.authentication.AuthenticationResponse;
import com.library.libraryapi.models.profile.ProfileResponse;
import com.library.libraryapi.models.register.RegisterRequest;
import com.library.libraryapi.models.register.RegisterResponse;
import com.library.libraryapi.models.token.Token;
import com.library.libraryapi.models.user.LibraryUserDetails;
import com.library.libraryapi.models.user.Role;
import com.library.libraryapi.repositories.TokenRepository;
import com.library.libraryapi.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final AuthProvider authProvider;
    private final AuthenticationManager authenticationManager;
    private final JWTService JWTService;

    public RegisterResponse register(RegisterRequest req) {

        LibraryUserDetails userDetails = new LibraryUserDetails();

        if (req.getEmail() != null && !req.getEmail().isEmpty())
            userDetails.setEmail(req.getEmail());
        else
            return null;

        if (req.getUsername() != null && !req.getUsername().isEmpty()) {
            userDetails.setUsername(req.getUsername());
        } else
            return null;
        if (req.getPassword() != null && !req.getPassword().isEmpty())
            userDetails.setPassword(authProvider.passwordEncoder().encode(req.getPassword()));
        else
            return null;

        userDetails.setRole(Role.USER);

        if (userRepository.existsByEmail(req.getEmail())) {
            return RegisterResponse.builder()
                    .error("Email already exists")
                    .build();
        }

        userRepository.save(userDetails);

        return RegisterResponse.builder()
                .message("User registered successfully")
                .build();
    }

    public AuthenticationResponse login(AuthenticationRequest req) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    req.getEmail(),
                    req.getPassword()));
        } catch (AuthenticationException e) {
            if (e.getMessage().contains("Bad credentials") || e.getMessage().contains("User not found")) {
                return AuthenticationResponse.builder()
                        .error("Authentication failed: " + e.getMessage())
                        .build();
            }
            return null;
        }

        Optional<LibraryUserDetails> userDetailsOptional = userRepository.findByEmail(req.getEmail());
        if (!userDetailsOptional.isPresent()) {
            return null;
        }
        LibraryUserDetails userDetails = userDetailsOptional.get();

        List<Token> validTokens = tokenRepository.findAllValidByEmail(userDetails.getEmail(),
                System.currentTimeMillis());

        if (validTokens == null)
            return null;

        if (!validTokens.isEmpty()) {
            return AuthenticationResponse.builder()
                    .token(validTokens.get(0).getToken())
                    .build();
        }

        Token token = JWTService.generateEmptyToken(userDetails);
        tokenRepository.save(token);

        return AuthenticationResponse.builder()
                .token(token.getToken())
                .build();
    }

    public String logout(String token) {
        Optional<Token> tokenOptional = tokenRepository.findByToken(token);

        if (!tokenOptional.isPresent()) {
            return null;
        }

        Token tokenEntity = tokenOptional.get();

        tokenEntity.setExpiration(System.currentTimeMillis());
        tokenRepository.delete(tokenEntity);
        SecurityContextHolder.clearContext();

        return "Logged out successfully";
    }

    public ProfileResponse getProfile(String token) {
        Optional<LibraryUserDetails> userDetailsOptional = userRepository.findByEmail(JWTService.extractEmail(token));

        if (!userDetailsOptional.isPresent()) {
            return null;
        }

        LibraryUserDetails userDetails = userDetailsOptional.get();
        return ProfileResponse.builder()
                .email(userDetails.getEmail())
                .username(userDetails.getUsername())
                .build();

    }

    public ProfileResponse updateProfile(RegisterRequest req) {
        Optional<LibraryUserDetails> userDetailsOptional = userRepository.findByEmail(req.getEmail());

        if (!userDetailsOptional.isPresent()) {
            return null;
        }

        LibraryUserDetails userDetails = userDetailsOptional.get();

        if (req.getUsername() != null && !req.getUsername().isEmpty()) {
            userDetails.setUsername(req.getUsername());
        }

        if (req.getPassword() != null && !req.getPassword().isEmpty()) {
            userDetails.setPassword(authProvider.passwordEncoder().encode(req.getPassword()));
        }

        userRepository.save(userDetails);

        return ProfileResponse.builder()
                .email(userDetails.getEmail())
                .username(userDetails.getUsername())
                .build();
    }


    public String deleteProfile(String token) {
        Optional<LibraryUserDetails> userDetailsOptional = userRepository.findByEmail(JWTService.extractEmail(token));

        if (!userDetailsOptional.isPresent()) {
            return null;
        }
        LibraryUserDetails userDetails = userDetailsOptional.get();
        logout(token);
        userRepository.delete(userDetails);
        return "Profile deleted successfully";
    }

}
