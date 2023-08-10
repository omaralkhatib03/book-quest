package com.library.libraryapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.library.libraryapi.models.user.LibraryUserDetails;

public interface UserRepository extends JpaRepository<LibraryUserDetails, Integer>{
    Optional<LibraryUserDetails> findByEmail(String email);
    Optional<LibraryUserDetails> findByUsername(String username);
    Boolean existsByEmail(String email);

}
