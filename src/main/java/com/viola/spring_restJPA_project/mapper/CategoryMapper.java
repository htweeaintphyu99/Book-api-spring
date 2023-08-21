package com.viola.spring_restJPA_project.mapper;

import java.util.List;

import com.viola.spring_restJPA_project.models.dto.CategoryDto;
import com.viola.spring_restJPA_project.models.entities.Category;
import com.viola.spring_restJPA_project.models.requests.CategoryRequest;
import com.viola.spring_restJPA_project.models.responses.CategoryResponse;

public interface CategoryMapper {
	CategoryDto toDto(CategoryRequest request);

	CategoryDto toDto(Category category);
	
	CategoryResponse toResponse(CategoryDto categoryDto);

	List<CategoryDto> toDto(List<Category> categories);

	List<CategoryResponse> toResponse(List<CategoryDto> categories);
}

