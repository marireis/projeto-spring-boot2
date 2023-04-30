package com.educandoweb.projeto.spring.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.projeto.spring.entites.User;


public interface UserRepository extends JpaRepository<User, Long>{

}
