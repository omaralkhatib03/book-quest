package com.library.libraryapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.library.libraryapi.models.token.Token;

public interface TokenRepository extends JpaRepository<Token, Long>{

    @Query(value = "select t from Token t where t.user.email = :email and t.expiration > :now")
    List<Token> findAllValidByEmail(@Param("email") String email, @Param("now") long now);

    Optional<Token> findByToken(String token);
    void deleteByToken(String token);
}

