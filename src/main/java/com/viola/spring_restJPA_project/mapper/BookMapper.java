package com.viola.spring_restJPA_project.mapper;

import com.viola.spring_restJPA_project.models.dto.BookDto;
import com.viola.spring_restJPA_project.models.entities.Book;
import com.viola.spring_restJPA_project.models.requests.BookRequest;
import com.viola.spring_restJPA_project.models.responses.BookResponse;

public interface BookMapper {

	BookDto toDto(BookRequest request);

	BookDto toDto(Book book);
	
	BookResponse toResponse(BookDto bookDto);

}
