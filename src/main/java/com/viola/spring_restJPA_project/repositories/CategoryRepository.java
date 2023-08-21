package com.viola.spring_restJPA_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viola.spring_restJPA_project.models.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	public Category findByCategoryName(String categoryName);
}
