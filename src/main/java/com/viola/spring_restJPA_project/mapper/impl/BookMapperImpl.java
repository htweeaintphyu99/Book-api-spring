package com.viola.spring_restJPA_project.mapper.impl;

import org.springframework.stereotype.Component;

import com.viola.spring_restJPA_project.mapper.AuthorMapper;
import com.viola.spring_restJPA_project.mapper.BookMapper;
import com.viola.spring_restJPA_project.mapper.CategoryMapper;
import com.viola.spring_restJPA_project.models.dto.BookDto;
import com.viola.spring_restJPA_project.models.entities.Book;
import com.viola.spring_restJPA_project.models.requests.BookRequest;
import com.viola.spring_restJPA_project.models.responses.BookResponse;

@Component
public class BookMapperImpl implements BookMapper {

	private final AuthorMapper authorMapper;

	private final CategoryMapper categoryMapper;

	public BookMapperImpl(AuthorMapper authorMapper, CategoryMapper categoryMapper) {
		this.authorMapper = authorMapper;
		this.categoryMapper = categoryMapper;
	}

	@Override
	public BookDto toDto(BookRequest request) {

		BookDto bookDto = new BookDto();
		if (request == null) {
			return null;
		}
		bookDto.setName(request.getName());
		bookDto.setAuthorIds(request.getAuthorIds());
		bookDto.setCategoryIds(request.getCategoryIds());
		return bookDto;
	}

	@Override
	public BookDto toDto(Book book) {
		if (book == null) {
			return null;
		}
		BookDto bookDto = new BookDto();
		bookDto.setId(book.getBookId());
		bookDto.setName(book.getBookName());
		bookDto.setCategories(categoryMapper.toDto(book.getCategories()));
		bookDto.setAuthors(authorMapper.toDto(book.getAuthors()));
		return bookDto;
	}

	@Override
	public BookResponse toResponse(BookDto bookDto) {

		if (bookDto == null) {
			return null;
		}

		BookResponse response = new BookResponse();
		response.setId(bookDto.getId());
		response.setName(bookDto.getName());
		response.setAuthors(authorMapper.toResponse(bookDto.getAuthors()));
		response.setCategories(categoryMapper.toResponse(bookDto.getCategories()));
		return response;
	}

}
