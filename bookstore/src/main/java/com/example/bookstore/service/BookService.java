package com.example.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.dto.BookDTO;
import com.example.bookstore.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;

	public BookDTO createBook(BookDTO bookDTO) {
		Book book =new Book();
		book.setBookId(UUID.randomUUID().toString());
		book.setTitle(bookDTO.getTitle());
		book.setIsbn(bookDTO.getIsbn());
		book.setPrice(bookDTO.getPrice());
		book.setAuthor(bookDTO.getAuthor());
		
		book=bookRepository.save(book);
		bookDTO.setBookId(book.getBookId());
		return bookDTO;
	}
	public List<Book> getAllBooks(){
		List<Book>books=bookRepository.findAll();
		List<BookDTO>bookDTOs=new ArrayList<>();
		for(Book book:books) {
			BookDTO bookDTO=new BookDTO();
			bookDTO.setBookId(book.getBookId());
			bookDTO.setTitle(book.getTitle());
			bookDTO.setAuthor(book.getAuthor());
			bookDTO.setIsbn(book.getIsbn());
			bookDTO.setPrice(book.getPrice());
			bookDTOs.add(bookDTO);
		}
		return books;
		
	}
	public BookDTO getBookById(Long id) {
		Book book=bookRepository.findById(id).get();
		BookDTO bookDTO=new BookDTO();
		bookDTO.setBookId(book.getBookId());
		bookDTO.setTitle(book.getTitle());
		bookDTO.setAuthor(book.getAuthor());
		bookDTO.setIsbn(book.getIsbn());
		bookDTO.setPrice(book.getPrice());
		return bookDTO;
	}
	public BookDTO updateBook(Long id,BookDTO bookDTO) {
		Book book=bookRepository.findById(id).get() ;
		book.setTitle(bookDTO.getTitle());
		book.setAuthor(bookDTO.getAuthor());
		book.setIsbn(bookDTO.getIsbn());
		book.setPrice(bookDTO.getPrice());
		book=bookRepository.save(book);
		bookDTO.setId(book.getId());
		return bookDTO;
	}
	public void deleteById(Long id) {
		bookRepository.deleteById(id);
	}
		
	

}
