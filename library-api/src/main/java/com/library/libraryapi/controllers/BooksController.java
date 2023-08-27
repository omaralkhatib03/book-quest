package com.library.libraryapi.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.libraryapi.models.book.Book;
import com.library.libraryapi.search.AuthorSearchRequest;
import com.library.libraryapi.search.TitleSearchRequest;
import com.library.libraryapi.services.BookService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class BooksController {

    private final BookService bookService;

    @GetMapping()
    public ResponseEntity<List<Book>> getBooks() {

        List<Book> res = bookService.getAllBooks();
        if (res == null) {
            return ResponseEntity.notFound().build();
        }

        if (res.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(res);
    }

    @GetMapping("/Periodicals")
    public ResponseEntity<List<Book>> getPeriodicals() {

        List<Book> res = bookService.getPeriodicals();
        if (res == null) {
            return ResponseEntity.notFound().build();
        }

        if (res.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(res);
    }

    @GetMapping("/NonFiction")
    public ResponseEntity<List<Book>> getNonFicion() {
        List<Book> res = bookService.getNonFiction();
        if (res == null) {
            return ResponseEntity.notFound().build();
        }

        if (res.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(res);
    }

    @GetMapping("/Fiction")
    public ResponseEntity<List<Book>> getFiction() {

        List<Book> res = bookService.getFiction();
        if (res == null) {
            return ResponseEntity.notFound().build();
        }

        if (res.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(res);
    }

    @PostMapping("/author")
    public ResponseEntity<List<Book>> searchByAuthor(@RequestBody AuthorSearchRequest entity) {
        List<Book> res = bookService.searchByAuthor(entity.getAuthor());
        if (res == null) {
            return ResponseEntity.notFound().build();
        }

        if (res.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(res);
    }


    @PostMapping("/title")
    public ResponseEntity<List<Book>> searchByTitle(@RequestBody TitleSearchRequest entity) {
        List<Book> res = bookService.searchByTitle(entity.getTitle());
        if (res == null) {
            return ResponseEntity.notFound().build();
        }

        if (res.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(res);
    }


}
