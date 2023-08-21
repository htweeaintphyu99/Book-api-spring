package com.viola.spring_restJPA_project.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viola.spring_restJPA_project.mapper.CategoryMapper;
import com.viola.spring_restJPA_project.models.dto.CategoryDto;
import com.viola.spring_restJPA_project.models.entities.Category;
import com.viola.spring_restJPA_project.repositories.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryMapper categoryMapper;

	public List<CategoryDto> getCategories() {
		List<Category> categories = categoryRepository.findAll();
		List<CategoryDto> categoryDto = categories.stream().map(c -> categoryMapper.toDto(c))
				.collect(Collectors.toList());
		return categoryDto;
	}

	public CategoryDto getCategoryById(Long categoryId) {
		Category category = categoryRepository.findById(categoryId).get();
		return categoryMapper.toDto(category);
	}

	public CategoryDto saveCategory(CategoryDto categoryDto) {
		Category category = new Category();
		category.setName(categoryDto.getName());
		Category savedCategory = categoryRepository.save(category); 
		return categoryMapper.toDto(savedCategory);
	}

	public CategoryDto deleteCategory(Long categoryId) {
		Category category = categoryRepository.findById(categoryId).get();
		if (category == null) {
			return null;
		}
		categoryRepository.deleteById(categoryId);
		return categoryMapper.toDto(category);

	}

	public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
		Category category = categoryRepository.findById(categoryId).get();
		if (category != null) {
			category.setCategoryName(categoryDto.getName());
			Category savedcategory = categoryRepository.save(category);
			return categoryMapper.toDto(savedcategory);

		}
		return null;

	}
}
