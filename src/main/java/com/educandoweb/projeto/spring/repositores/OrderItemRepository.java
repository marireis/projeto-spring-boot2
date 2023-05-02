package com.educandoweb.projeto.spring.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.projeto.spring.entites.OrderItem;


public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
