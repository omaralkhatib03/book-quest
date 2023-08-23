package com.library.libraryapi_2_7_14.models.user;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Entity
@Builder
@RequiredArgsConstructor
public class LibraryUserDetails extends User implements UserDetails{

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.getRole().name()));
    }

    @Override
    public boolean isAccountNonExpired() { // all users registered are valid
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // al users registered are unlocked
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // all users registered have valid credentials
    }

    @Override
    public boolean isEnabled() {
        return true; // all users registered are enabled
    }
    
}
