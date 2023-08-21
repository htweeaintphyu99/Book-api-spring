package com.viola.spring_restJPA_project.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.viola.spring_restJPA_project.mapper.AuthorMapper;
import com.viola.spring_restJPA_project.models.dto.AuthorDto;
import com.viola.spring_restJPA_project.models.entities.Author;
import com.viola.spring_restJPA_project.models.requests.AuthorRequest;
import com.viola.spring_restJPA_project.models.responses.AuthorResponse;

@Component
public class AuthorMapperImpl implements AuthorMapper{
	public AuthorDto toDto(AuthorRequest request) {
		AuthorDto authorDto = new AuthorDto();
		authorDto.setName(request.getName());
		authorDto.setEmail(request.getEmail());
		return authorDto;
	}
	
	public AuthorDto toDto(Author author) {
		AuthorDto authorDto = new AuthorDto();
		authorDto.setName(author.getAuthorName());
		authorDto.setId(author.getAuthorId());
		return authorDto;
	}
	
	public AuthorResponse toResponse(AuthorDto bookDto) {
		AuthorResponse authorResponse = new AuthorResponse();
		authorResponse.setId(bookDto.getId());
		authorResponse.setName(bookDto.getName());
		return authorResponse;
	}

	@Override
	public List<AuthorDto> toDto(List<Author> authors) {
		
		return authors.stream().map(a->toDto(a)).collect(Collectors.toList());
	}

	@Override
	public List<AuthorResponse> toResponse(List<AuthorDto> authors) {
		
		return authors.stream().map(a->toResponse(a)).collect(Collectors.toList());
	}

}
