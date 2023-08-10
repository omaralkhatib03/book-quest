package com.library.libraryapi.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.library.libraryapi.models.book.Book;


public interface BookRepository extends JpaRepository<Book, String>{
    List<Book> findAll();  
    
    @Query("select b from Book b, Periodical p where b.ISBN = p.book.ISBN")
    List<Book> findPeriodicals();


    @Query("select b from Book b, Fiction f where b.ISBN = f.book.ISBN")
    List<Book> findFiction();

    @Query("select b from Book b, NonFiction n where b.ISBN = n.book.ISBN")
    List<Book> findNonFiction();

    @Query("select b from Book b where b.author like %:author%")    
    List<Book> searchByAuthor(@Param("author") String author);

    @Query("select b from Book b where b.title like %:title%")    
    List<Book> searchByTitle(@Param("title") String title);


}
