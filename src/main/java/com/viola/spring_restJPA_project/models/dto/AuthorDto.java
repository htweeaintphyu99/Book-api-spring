package com.viola.spring_restJPA_project.models.dto;

import java.util.List;

public class AuthorDto {
	private Long id;
	private String name;
	private String email;
	private List<BookDto> bookNames;

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BookDto> getBookNames() {
		return bookNames;
	}

	public void setBookNames(List<BookDto> bookNames) {
		this.bookNames = bookNames;
	}


}
