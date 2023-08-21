package com.viola.spring_restJPA_project.mapper;

import java.util.List;

import com.viola.spring_restJPA_project.models.dto.AuthorDto;
import com.viola.spring_restJPA_project.models.entities.Author;
import com.viola.spring_restJPA_project.models.requests.AuthorRequest;
import com.viola.spring_restJPA_project.models.responses.AuthorResponse;

public interface AuthorMapper {
	AuthorDto toDto(AuthorRequest request);

	AuthorDto toDto(Author author);

	AuthorResponse toResponse(AuthorDto bookDto);

	List<AuthorDto> toDto(List<Author> authors);

	List<AuthorResponse> toResponse(List<AuthorDto> authors);

}
