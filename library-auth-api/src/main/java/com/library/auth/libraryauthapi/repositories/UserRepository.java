package com.library.auth.libraryauthapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.auth.libraryauthapi.models.user.LibraryUserDetails;

public interface UserRepository extends JpaRepository<LibraryUserDetails, Integer>{
    Optional<LibraryUserDetails> findByEmail(String email);
    Optional<LibraryUserDetails> findByUsername(String username);
    Boolean existsByEmail(String email);

}
