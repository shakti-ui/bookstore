package com.example.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.dto.BookDTO;
import com.example.bookstore.service.BookService;

@RestController
public class BookController {
	@Autowired
	private BookService bookService;
	
	@PostMapping(value="/book/add")
	public ResponseEntity<?>addBook(@RequestBody BookDTO bookDTO){
		return new ResponseEntity<>(bookService.createBook(bookDTO),HttpStatus.CREATED);
	}
	@GetMapping(value="/book/getAll")
	public ResponseEntity<?>getAllBooks(){
		List<Book> bookList=bookService.getAllBooks();
		ResponseEntity responseEntity=new ResponseEntity<>(bookList,HttpStatus.OK);
		return responseEntity;
	}
	@GetMapping(value="/book/get-by-id")
	public ResponseEntity<?>getById(@RequestParam Long id){
	BookDTO book=	bookService.getBookById(id);
		return new ResponseEntity<>(book,HttpStatus.OK);
	}
	@DeleteMapping(value="/book/id")
	public ResponseEntity<?>deleteById(@RequestParam Long id){
		bookService.deleteById(id);
		return new ResponseEntity<>("book deleted successfully",HttpStatus.OK);
	}
	@GetMapping(value="/book/list-books-by-pagination")
	public ResponseEntity<?>listBookByPagination(@RequestParam Integer pageNumber,@RequestParam Integer pageSize){
		List<BookDTO> bookList=bookService.listBookByPageNumber(pageNumber, pageSize);
		ResponseEntity responseEntity=new ResponseEntity<>(bookList,HttpStatus.OK); 
		return responseEntity;
		
	}
	
	

}
