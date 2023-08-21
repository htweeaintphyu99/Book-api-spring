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

import com.viola.spring_restJPA_project.mapper.AuthorMapper;
import com.viola.spring_restJPA_project.models.dto.AuthorDto;
import com.viola.spring_restJPA_project.models.requests.AuthorRequest;
import com.viola.spring_restJPA_project.models.responses.AuthorResponse;
import com.viola.spring_restJPA_project.services.AuthorService;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
	
	private final AuthorMapper authorMapper;
	
	@Autowired
	AuthorService authorService;
	
	public AuthorController(AuthorMapper authorMapper) {
		this.authorMapper = authorMapper;
	}
	
	@GetMapping
	public List<AuthorResponse> getAllAuthors() {
		List<AuthorDto> authorDto = authorService.getAuthors();
		List<AuthorResponse> authorResponse = authorDto.stream().map(c -> authorMapper.toResponse(c))
				.collect(Collectors.toList());
		return authorResponse;
	}
	
	@PostMapping
	public AuthorResponse createAuthor(@RequestBody AuthorRequest request) {
		AuthorDto authorDto = authorMapper.toDto(request);
		AuthorDto savedAuthor = authorService.saveAuthor(authorDto);
		return authorMapper.toResponse(savedAuthor);
	}
	
	@PutMapping("/{authorId}")
	public AuthorResponse updateAuthor(@RequestBody AuthorRequest request, @PathVariable Long authorId) {
		AuthorDto authorDto = authorMapper.toDto(request);
		AuthorDto updatedAuthor = authorService.updateAuthor(authorDto,authorId);
		return authorMapper.toResponse(updatedAuthor);
	}
	
	@DeleteMapping("/{authorId}")
	public AuthorResponse deleteAuthor(@PathVariable Long authorId) {
		AuthorDto authorDto = authorService.deleteAuthor(authorId);
		return authorMapper.toResponse(authorDto);
	}
	
	
}
