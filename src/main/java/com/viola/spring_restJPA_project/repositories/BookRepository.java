package com.viola.spring_restJPA_project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viola.spring_restJPA_project.models.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
	public List<Book> findByBookNameContaining(String bookName);
}
