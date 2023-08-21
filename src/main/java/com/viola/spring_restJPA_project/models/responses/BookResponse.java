package com.viola.spring_restJPA_project.models.responses;

import java.util.List;

public class BookResponse {
	
	private Long id;
	private String name;
	private List<CategoryResponse> categories;
	private List<AuthorResponse> authors;

	public Long getId() {
		return id;
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

	public List<CategoryResponse> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryResponse> categories) {
		this.categories = categories;
	}

	public List<AuthorResponse> getAuthors() {
		return authors;
	}

	public void setAuthors(List<AuthorResponse> authors) {
		this.authors = authors;
	}

}
