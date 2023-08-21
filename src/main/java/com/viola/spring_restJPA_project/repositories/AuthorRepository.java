package com.viola.spring_restJPA_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viola.spring_restJPA_project.models.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{

}
