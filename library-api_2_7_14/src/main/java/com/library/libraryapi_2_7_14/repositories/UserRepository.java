package com.library.libraryapi_2_7_14.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.library.libraryapi_2_7_14.models.user.LibraryUserDetails;

public interface UserRepository extends JpaRepository<LibraryUserDetails, Integer>{
    Optional<LibraryUserDetails> findByEmail(String email);
    Optional<LibraryUserDetails> findByUsername(String username);
    Boolean existsByEmail(String email);

}
