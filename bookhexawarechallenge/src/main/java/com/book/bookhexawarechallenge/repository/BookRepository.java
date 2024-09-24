package com.book.bookhexawarechallenge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.book.bookhexawarechallenge.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	 @Query("select b from Book b where b.isbn=?1")
	 Book findByIsbn(String isbn);
	 
	 @Query("select b from Book b where b.isbn=?1")
	 Optional<Book>findByIsbndelete(String isbn);
	 

}
