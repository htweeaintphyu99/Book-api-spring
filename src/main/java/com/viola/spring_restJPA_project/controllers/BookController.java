package com.viola.spring_restJPA_project.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viola.spring_restJPA_project.mapper.BookMapper;
import com.viola.spring_restJPA_project.mapper.CategoryMapper;
import com.viola.spring_restJPA_project.models.dto.BookDto;
import com.viola.spring_restJPA_project.models.requests.BookRequest;
import com.viola.spring_restJPA_project.models.responses.BookResponse;
import com.viola.spring_restJPA_project.services.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private final BookMapper bookMapper;

	@Autowired
	CategoryMapper categoryMapper;

	@Autowired
	BookService bookService;

	public BookController(BookMapper bookMapper) {
		this.bookMapper = bookMapper;
	}

	@GetMapping
	public List<BookResponse> getAllBooks() {
		List<BookDto> books = bookService.getBooks();

		return books.stream().map(bookMapper::toResponse).collect(Collectors.toList());
	}

	@GetMapping("/{bookId}")
	public BookResponse getBookById(@PathVariable Long bookId) {
		BookDto bookDto = bookService.getBookById(bookId);

		return bookMapper.toResponse(bookDto);
	}
	
	@GetMapping("/books-containing-{bookName}")
	public List<BookResponse> getBooksByNameContaining(@PathVariable String bookName) {
		List<BookDto> books = bookService.getBooksByNameContaining(bookName);
		List<BookResponse> bookResponse = books.stream().map(b -> bookMapper.toResponse(b))
				.collect(Collectors.toList());
		return bookResponse;
	}
	
	@PostMapping
	public BookResponse createBook(@RequestBody BookRequest request) {
		BookDto bookDto = bookMapper.toDto(request);
		BookDto saveBook = bookService.saveBook(bookDto);
		return bookMapper.toResponse(saveBook);
	}

	@PutMapping("/{bookId}")
	public BookResponse updateBook(@RequestBody BookRequest request, @PathVariable Long bookId) {
		BookDto bookDto = bookMapper.toDto(request);
		BookDto saveBook = bookService.updateBook(bookDto, bookId);
		return bookMapper.toResponse(bookDto);
	}
	


	@DeleteMapping("/{bookId}")
	public BookResponse deleteBook(@PathVariable Long bookId) {
		BookDto bookDto = bookService.deleteBook(bookId);
		return bookMapper.toResponse(bookDto);
	}
}
