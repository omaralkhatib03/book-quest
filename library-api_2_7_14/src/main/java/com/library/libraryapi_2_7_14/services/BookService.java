package com.library.libraryapi_2_7_14.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.libraryapi_2_7_14.models.book.Book;
import com.library.libraryapi_2_7_14.repositories.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getPeriodicals() {
        return bookRepository.findPeriodicals();
    }

    public List<Book> getNonFiction() {
        return bookRepository.findNonFiction();
    }

    public List<Book> getFiction() {
        return bookRepository.findFiction();
    }

    public List<Book> searchByAuthor(String author) {
        return bookRepository.searchByAuthor(author);
    }

    public List<Book> searchByTitle(String title) {
        return bookRepository.searchByTitle(title);
    }

}
