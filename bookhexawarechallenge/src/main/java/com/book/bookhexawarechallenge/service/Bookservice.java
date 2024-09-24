package com.book.bookhexawarechallenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.bookhexawarechallenge.exception.InvalidIdException;
import com.book.bookhexawarechallenge.model.Book;
import com.book.bookhexawarechallenge.repository.BookRepository;

@Service
public class Bookservice {
	
	
	@Autowired
	private BookRepository bookrepository;
	
	public Book add(Book book) {
		return bookrepository.save(book);
		
	}
	
	public List<Book> getAllBooks() {
        return bookrepository.findAll(); 
    }
	
		public Book getBookByIsbn(String isbn) {
		// TODO Auto-generated method stub
		return bookrepository.findByIsbn(isbn);
	}
	

		public Book updateBook(int id, Book newBook) throws InvalidIdException {
	        Optional<Book> optionalBook = bookrepository.findById(id);
	        if (optionalBook.isEmpty()) {
	            throw new InvalidIdException("Invalid Book ID Given");
	        }

	        Book bookDB = optionalBook.get();
	        bookDB.setTitle(newBook.getTitle());
	        bookDB.setAuthor(newBook.getAuthor());
	        bookDB.setPublicationYear(newBook.getPublicationYear());
	        bookDB.setIsbn(newBook.getIsbn());
	      //  bookDB.setAdmin(newBook.getAdmin());
	        return bookrepository.save(bookDB);
	    }

		public void deleteBookByIsbn(String isbn) throws InvalidIdException {
	        Optional<Book> optionalBook = bookrepository.findByIsbndelete(isbn);
	        if (optionalBook.isEmpty()) {
	            throw new InvalidIdException("Invalid ISBN Given");
	        }

	        Book book = optionalBook.get();
	        bookrepository.delete(book);
	    }
		
		

}
