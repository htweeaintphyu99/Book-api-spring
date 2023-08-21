package com.viola.spring_restJPA_project.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.viola.spring_restJPA_project.mapper.CategoryMapper;
import com.viola.spring_restJPA_project.models.dto.CategoryDto;
import com.viola.spring_restJPA_project.models.entities.Category;
import com.viola.spring_restJPA_project.models.requests.CategoryRequest;
import com.viola.spring_restJPA_project.models.responses.CategoryResponse;

@Component
public class CategoryMapperImpl implements CategoryMapper {
	
	public CategoryDto toDto(CategoryRequest categoryResquest) {
		if (categoryResquest == null) {
			return null;
		}
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setName(categoryResquest.getName());
		return categoryDto;

	}

	public CategoryDto toDto(Category category) {
		if (category == null) {
			return null;
		}
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setName(category.getName());
		categoryDto.setId(category.getId());
		return categoryDto;
	}

	public CategoryResponse toResponse(CategoryDto categoryDto) {
		if (categoryDto == null) {
			return null;
		}

		CategoryResponse categoryResponse = new CategoryResponse();
		categoryResponse.setName(categoryDto.getName());
		categoryResponse.setId(categoryDto.getId());
		return categoryResponse;
	}

	@Override
	public List<CategoryDto> toDto(List<Category> categories) {
		return categories.stream().map(c->toDto(c)).collect(Collectors.toList());
	}

	@Override
	public List<CategoryResponse> toResponse(List<CategoryDto> categories) {
		return categories.stream().map(c->toResponse(c)).collect(Collectors.toList());
	}
}
