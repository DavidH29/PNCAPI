package com.uca.proyecto.ncapas.grupo.cuatro.services;

import java.util.List;

import com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs.PetDTO;
import com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs.SignUpDTO;
import com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs.TokenDTO;
import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Pet;
import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Status;
import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Ticket;
import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.User;


public interface UserServices {
	//Admin validation
	boolean isAdmin();
	
	// User methods
	void insertNewUser(SignUpDTO userInfo, Boolean v);
	User findOneUserByEmail(String email);
	User findOneUserByName(String username);
	Ticket createNewTicket(PetDTO petDTO, TokenDTO token);
	void insertTicket(Ticket ticket);
	String getUserName();
	Ticket findOneTicketById(int id);
	void deleteOneTicket(Ticket ticket);
	List<Status> findAllStatusByAvailable();
	List<Pet> findAllPetAdoptedByMe();
	List<Pet> findAllPetAdoptedByMe2(TokenDTO token);
	List<Ticket> findAllMyTickets();
	
	// Tokens Methods
	Boolean isTokenValid(User user, String token);
	void insertToken(User user, String token);
	boolean comparePassoword(User user, String passsoword);
	
}
