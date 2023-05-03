package com.educandoweb.projeto.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.projeto.spring.entites.User;
import com.educandoweb.projeto.spring.repositores.UserRepository;
import com.educandoweb.projeto.spring.resource.exceptions.DatabaseException;
import com.educandoweb.projeto.spring.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);			
		} catch (EmptyResultDataAccessException e) {//não está retornando erro 404 no postman
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {//quando quer deletar usuario relacionado com outras tabelas
			throw new DatabaseException(e.getMessage());//lançando exceção da minha camada de serviço
		}
	}
	
	public User update(Long id, User obj) {
		try {		
			User entity = repository.getReferenceById(id);//monitora o obje no jpa para mexer
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User entity, User obj) {//metodo para atualizar o dados do entity
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
		
	}
}
