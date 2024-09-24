package com.book.bookhexawarechallenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.bookhexawarechallenge.dto.MessageDto;
import com.book.bookhexawarechallenge.exception.InvalidIdException;
import com.book.bookhexawarechallenge.model.Book;
import com.book.bookhexawarechallenge.service.Bookservice;


@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	private Bookservice bookservice;
	
	
	@PostMapping("/add")
	public Book addbook(@RequestBody Book book) {
		return bookservice.add(book);
	}
	
	@GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookservice.getAllBooks();
        return ResponseEntity.ok(books); 
    }
	
	@GetMapping("/{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn) {
        return bookservice.getBookByIsbn(isbn);
    }
	
	@PutMapping("/update/{id}")
    public ResponseEntity<?> updateBook(@PathVariable int id, @RequestBody Book newBook, MessageDto dto) {
        try {
            Book updatedBook = bookservice.updateBook(id, newBook);
            return ResponseEntity.ok(updatedBook); 
        } catch (InvalidIdException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }
	
	@DeleteMapping("/delete/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable String isbn, MessageDto dto) {
        try {
            bookservice.deleteBookByIsbn(isbn);
            dto.setMsg("Book Deleted Successfully");
            return ResponseEntity.ok(dto);
        } catch (InvalidIdException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }

}
