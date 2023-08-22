package com.viola.spring_restJPA_project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.viola.spring_restJPA_project.mapper.BookMapper;
import com.viola.spring_restJPA_project.models.dto.BookDto;
import com.viola.spring_restJPA_project.models.entities.Author;
import com.viola.spring_restJPA_project.models.entities.Book;
import com.viola.spring_restJPA_project.models.entities.Category;
import com.viola.spring_restJPA_project.repositories.AuthorRepository;
import com.viola.spring_restJPA_project.repositories.BookRepository;
import com.viola.spring_restJPA_project.repositories.CategoryRepository;

@Service
public class BookService {

	private final BookRepository bookRepository;

	private final CategoryRepository categoryRepository;

	private final AuthorRepository authorRepository;

	private final BookMapper bookMapper;

	public BookService(BookRepository bookRepository, CategoryRepository categoryRepository,
			AuthorRepository authorRepository, BookMapper bookMapper) {

		this.bookRepository = bookRepository;
		this.categoryRepository = categoryRepository;
		this.authorRepository = authorRepository;
		this.bookMapper = bookMapper;
	}

	public BookDto saveBook(BookDto bookDto) {
		Book book = new Book();
		book.setBookName(bookDto.getName());
		List<Author> authors = new ArrayList<>();
		List<Long> authorIds = bookDto.getAuthorIds();
		for (Long authorId : authorIds) {
			Author author = authorRepository.findById(authorId).orElse(null);
			authors.add(author);
		}
		book.setAuthors(authors);
		
		List<Category> categories = new ArrayList<>();
		List<Long> categoryIds = bookDto.getCategoryIds();
		for (Long categoryId : categoryIds) {
			Category category = categoryRepository.findById(categoryId).orElse(null);
			category.setBook(book);
			categories.add(category);
		}
		book.setCategories(categories);
		Book savedBook = bookRepository.save(book);
		return bookMapper.toDto(savedBook);

	}

	public List<BookDto> getBooks() {

		List<Book> books = bookRepository.findAll();
		// List<BookDto> bookDtos =
		// books.stream().map(b->bookMapper.toDto(b)).collect(Collectors.toList());
		return books.stream().map(bookMapper::toDto).collect(Collectors.toList());
	}

	public BookDto getBookById(Long bookId) {
		Optional<Book> optionalBook = bookRepository.findById(bookId);
		Book book = optionalBook.get();

		return bookMapper.toDto(book);
	}

	public List<BookDto> getBooksByNameContaining(String bookName) {
		List<Book> books = bookRepository.findByBookNameContaining(bookName);
		for (Book book : books) {
			System.out.println(book.getBookName());
		}
		List<BookDto> bookDto = books.stream().map(b -> bookMapper.toDto(b)).collect(Collectors.toList());
		return bookDto;
	}

	public BookDto deleteBook(Long bookId) {
		Book book = bookRepository.findById(bookId).get();
		if (book == null) {
			return null;
		}
		if (book.getAuthors().size() > 1) {
			for (Author author : book.getAuthors()) {
				book.removeAuthor(author);
			}
		}
		bookRepository.delete(book);
		BookDto bookDto = bookMapper.toDto(book);
		return bookDto;
	}

	public BookDto updateBook(BookDto bookDto, Long bookId) {
		Book book = bookRepository.findById(bookId).get();
		if (book == null || bookDto == null) {
			return null;
		}

		if (bookDto.getName() != book.getBookName()) {
			book.setBookName(bookDto.getName());
		}

		List<Author> existingAuthors = book.getAuthors();
		List<Long> existingAuthorIds = new ArrayList<>();
		for (Author au : existingAuthors) {
			System.out.println("Existing au-> " + au.getAuthorId());
			existingAuthorIds.add(au.getAuthorId());
		}

		List<Long> authorIds = bookDto.getAuthorIds();
		List<Long> authorIdsDeleted = existingAuthorIds.stream().filter(element -> !authorIds.contains(element))
				.collect(Collectors.toList());

		List<Long> authorIdsAdded = authorIds.stream().filter(element -> !existingAuthorIds.contains(element))
				.collect(Collectors.toList());

		for (Long auId : authorIdsDeleted) {
			System.out.println("Authors -> " + auId);
			Author author = authorRepository.findById(auId).orElse(null);
			existingAuthors.remove(author);
		}

		for (Long auId : authorIdsAdded) {
			Author author = authorRepository.findById(auId).orElse(null);
			existingAuthors.add(author);
		}
		System.out.println(existingAuthors.size());
		book.setAuthors(existingAuthors);
		Book savedbook = bookRepository.save(book);
		return bookMapper.toDto(savedbook);
	}

}
