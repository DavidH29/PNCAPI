package com.uca.proyecto.ncapas.grupo.cuatro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findOneByEmail(String email);
	User findOneByName(String name);
	User findOneById(int Id);
}
