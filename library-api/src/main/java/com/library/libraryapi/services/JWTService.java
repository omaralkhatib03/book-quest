package com.library.libraryapi.services;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.library.libraryapi.models.token.Token;
import com.library.libraryapi.models.user.LibraryUserDetails;
import com.library.libraryapi.repositories.TokenRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class JWTService {

    private final String SECRET_KEY = "FE3566B3FF689BF774144460B1E0C66B736E6BC56BC336082B9457F08168262A";
    private final TokenRepository tokenRepository;


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        } catch (Exception e) {
            System.out.println("Error parsing token: " + e.getMessage());
            if (e.getMessage().contains("JWT expired at")) {
                System.out.println("Token expired");
                tokenRepository.deleteByToken(token);
                SecurityContextHolder.clearContext();
            }
            return Jwts.claims(new HashMap<String, Object>());
        }
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Token generateToken(
            Map<String, Object> claims,
            LibraryUserDetails libraryUserDetails) {

        return Token.builder()
                .token(Jwts
                        .builder()
                        .setClaims(claims)
                        .setSubject(libraryUserDetails.getEmail())
                        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System. currentTimeMillis() + 1000 * 60 * 24))
                        .compact())
                .expiration(System.currentTimeMillis() + 1000 * 60 * 24)
                .issuedAt(System.currentTimeMillis())
                .user(libraryUserDetails)
                .build();
    }

    public Token generateEmptyToken(LibraryUserDetails libraryUserDetails) {
        return generateToken(Map.of(), libraryUserDetails);
    }

    public boolean validate(String token, LibraryUserDetails libraryUserDetails) {
        return (extractEmail(token).equals(libraryUserDetails.getEmail()) && !isTokenExpired(token)) &&
        (tokenRepository.findByToken(token).orElse(null) != null);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

}
