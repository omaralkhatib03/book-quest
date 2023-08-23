package com.library.libraryapi_2_7_14.auth;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.library.libraryapi_2_7_14.models.user.LibraryUserDetails;
import com.library.libraryapi_2_7_14.services.JWTService;
import com.library.libraryapi_2_7_14.services.LibraryUserDetailsService;

import io.micrometer.core.lang.NonNull;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final LibraryUserDetailsService libraryUserDetailsService;
    private final JWTService JWTService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            doFilter(request, response, filterChain);
            return;
        }
        final String token = authorizationHeader.substring(7);
        if (token.equals("null")) {
            doFilter(request, response, filterChain);
            return;
        }
        final String email = JWTService.extractEmail(token);

        
        if (email == null) {
            doFilter(request, response, filterChain);
            return;
        }
        
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            LibraryUserDetails libraryUserDetails = this.libraryUserDetailsService.loadUserByUsername(email);
            if (!JWTService.validate(token, libraryUserDetails))
            {
                filterChain.doFilter(request, response);
                return;
            }
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                libraryUserDetails, 
                null, 
                libraryUserDetails.getAuthorities()
            );
            authToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
            );
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(request, response);

    }
}
