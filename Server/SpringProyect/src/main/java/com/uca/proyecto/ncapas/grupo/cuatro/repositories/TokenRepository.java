package com.uca.proyecto.ncapas.grupo.cuatro.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Token;
import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.User;

public interface TokenRepository extends JpaRepository<Token, Long> {
	
	List<Token> findByUserAndActive(User user, Boolean active);
	Token findOneByContent(String content);
	
}
