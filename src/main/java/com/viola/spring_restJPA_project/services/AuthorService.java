package com.viola.spring_restJPA_project.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viola.spring_restJPA_project.mapper.AuthorMapper;
import com.viola.spring_restJPA_project.models.dto.AuthorDto;
import com.viola.spring_restJPA_project.models.entities.Author;
import com.viola.spring_restJPA_project.repositories.AuthorRepository;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private AuthorMapper authorMapper;

	public List<AuthorDto> getAuthors() {
		List<Author> authors = authorRepository.findAll();
		List<AuthorDto> authorDto = authors.stream().map(a -> authorMapper.toDto(a)).collect(Collectors.toList());
		return authorDto;
	}

	public AuthorDto saveAuthor(AuthorDto authorDto) {
		Author author = new Author();
		author.setAuthorName(authorDto.getName());
		author.setAuthorEmail(authorDto.getEmail());
		Author savedAuthor = authorRepository.save(author);
		return authorMapper.toDto(savedAuthor);
	}

	public AuthorDto deleteAuthor(Long authorId) {
		Author author = authorRepository.findById(authorId).get();
		authorRepository.deleteById(authorId);
		return authorMapper.toDto(author);
	}

	public AuthorDto updateAuthor(AuthorDto authorDto, Long authorId) {
		Author author = authorRepository.findById(authorId).get();
		if (author == null) {
			return null;
		}
		author.setAuthorName(authorDto.getName());
		Author savedAuthor = authorRepository.save(author);
		return authorMapper.toDto(savedAuthor);
	}

}
