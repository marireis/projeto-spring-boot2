package com.educandoweb.projeto.spring.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.projeto.spring.entites.Order;


public interface OrderRepository extends JpaRepository<Order, Long>{

}
