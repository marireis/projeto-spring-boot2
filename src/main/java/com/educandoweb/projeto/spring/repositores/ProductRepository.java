package com.educandoweb.projeto.spring.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.projeto.spring.entites.Product;


public interface ProductRepository extends JpaRepository<Product, Long>{

}
