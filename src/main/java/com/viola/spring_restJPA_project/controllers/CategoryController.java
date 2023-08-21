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

import com.viola.spring_restJPA_project.mapper.CategoryMapper;
import com.viola.spring_restJPA_project.models.dto.CategoryDto;
import com.viola.spring_restJPA_project.models.requests.CategoryRequest;
import com.viola.spring_restJPA_project.models.responses.CategoryResponse;
import com.viola.spring_restJPA_project.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	private final CategoryMapper categoryMapper;

	@Autowired
	CategoryService categoryService;

	public CategoryController(CategoryMapper categoryMapper) {
		this.categoryMapper = categoryMapper;
	}

	@GetMapping
	public List<CategoryResponse> getAllCategories() {
		List<CategoryDto> categoryDto = categoryService.getCategories();
		List<CategoryResponse> categoryResponse = categoryDto.stream().map(c -> categoryMapper.toResponse(c))
				.collect(Collectors.toList());
		return categoryResponse;
	}

	@GetMapping("/{categoryId}")
	public CategoryResponse getCategoryById(@PathVariable Long categoryId) {
		CategoryDto categoryDTO = categoryService.getCategoryById(categoryId);

		CategoryResponse categoryResponse = new CategoryResponse();
		categoryResponse.setId(categoryDTO.getId());
		categoryResponse.setName(categoryDTO.getName());

		return categoryResponse;
	}

	@PostMapping
	public CategoryResponse createCategory(@RequestBody CategoryRequest request) {
		CategoryDto categoryDto = categoryMapper.toDto(request);
		CategoryDto savedCategory = categoryService.saveCategory(categoryDto);
		return categoryMapper.toResponse(savedCategory);
		
	}
	
	@PutMapping("/{categoryId}")
	public CategoryResponse updateCategory(@RequestBody CategoryRequest request, @PathVariable Long categoryId) {
		CategoryDto categoryDto = categoryMapper.toDto(request);
		CategoryDto savedCategory = categoryService.updateCategory(categoryDto, categoryId);
		return categoryMapper.toResponse(savedCategory);
	}

	@DeleteMapping("/{categoryId}")
	public CategoryResponse deleteCategoryById(@PathVariable Long categoryId) {
		CategoryDto categoryDto = categoryService.deleteCategory(categoryId);
		return categoryMapper.toResponse(categoryDto);
	}

}
