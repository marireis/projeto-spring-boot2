package com.educandoweb.projeto.spring.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.projeto.spring.entites.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
