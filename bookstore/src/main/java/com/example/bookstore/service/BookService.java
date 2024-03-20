package com.example.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
		//book.setId(bookDTO.getId());
		book.setBookId(UUID.randomUUID().toString());
		book.setTitle(bookDTO.getTitle());
		book.setIsbn(bookDTO.getIsbn());
		book.setPrice(bookDTO.getPrice());
		book.setAuthor(bookDTO.getAuthor());
		
		book=bookRepository.save(book);
		//bookDTO.setBookId(book.getBookId());
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
		bookDTO.setId(book.getId());
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
	public List<BookDTO>listBookByPageNumber(int pageNumber,int pageSize){
//		Sort sort;
//		if (sortBook.equalsIgnoreCase("DESC")) {
//			sort=Sort.by(sortBy).descending();
//		}else {
//			sort=Sort.by(sortBy).ascending();
//		}
		PageRequest pageRequest=PageRequest.of(pageNumber, pageSize);
		Page<Book>page=bookRepository.findAll(pageRequest);
		List<BookDTO>books=new ArrayList<>();
		for(Book book:page.getContent()) {
			BookDTO bookDTO=new BookDTO();
			bookDTO.setId(book.getId());
			bookDTO.setBookId(book.getBookId());
			bookDTO.setTitle(book.getTitle());
			bookDTO.setAuthor(book.getAuthor());
			bookDTO.setIsbn(book.getIsbn());
			bookDTO.setPrice(book.getPrice());
			books.add(bookDTO);
		}
		return books;
		
	}
		
	

}
