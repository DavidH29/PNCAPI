package com.uca.proyecto.ncapas.grupo.cuatro.services.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs.PetDTO;
import com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs.SignUpDTO;
import com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs.TokenDTO;
import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Pet;
import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Status;
import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Ticket;
import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Token;
import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.User;
import com.uca.proyecto.ncapas.grupo.cuatro.repositories.StatusRepository;
import com.uca.proyecto.ncapas.grupo.cuatro.repositories.TicketRepository;
import com.uca.proyecto.ncapas.grupo.cuatro.repositories.TokenRepository;
import com.uca.proyecto.ncapas.grupo.cuatro.repositories.UserRepository;
import com.uca.proyecto.ncapas.grupo.cuatro.services.PetServices;
import com.uca.proyecto.ncapas.grupo.cuatro.services.UserServices;
import com.uca.proyecto.ncapas.grupo.cuatro.utils.TokenManager;

@Service
public class UserServicesImp implements UserServices{

	@Autowired
	private TokenRepository tokenRepository;
	
	@Autowired
	private TokenManager tokenManager;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private PetServices petServices;
	
	@Override
	public String getUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	@Override
	public void insertNewUser(SignUpDTO userInfo, Boolean v) {
		
		User user = new User();
		String encryptedPassword = encoder.encode(userInfo.getPassword());
		
		user.setName(userInfo.getName());
		user.setEmail(userInfo.getEmail());
		user.setPassword(encryptedPassword);
		user.setPhonenumber(Integer.parseInt(userInfo.getPhonenumber()));
		user.setAdmin(v);
		
		userRepository.save(user);
		
	}

	@Override
	public User findOneUserByEmail(String email) {
		return userRepository.findOneByEmail(email);
	}
	
	@Override
	public List<Pet> findAllPetAdoptedByMe() {
		
		User user = findOneUserByName(getUserName());
		
		List<Ticket> myTickets = ticketRepository.findByUser(user);
		List<Pet> myPets = new ArrayList<Pet>();
		
		myTickets.forEach(ticket -> 
		myPets.add(petServices.findOnePetById(ticket
				.getPet()
				.getId()))
				);
		return myPets;
	}
	
		@Override
		public List<Pet> findAllPetAdoptedByMe2(TokenDTO tokeninfo) {
			
			Token token = tokenRepository.findOneByContent(tokeninfo.getToken());
			
			
			User user = token.getUser();
			
			System.out.println("El usuario es" + user.getName());
			
			List<Ticket> myTickets = ticketRepository.findByUser(user);
			List<Pet> myPets = new ArrayList<Pet>();
			
			myTickets.forEach(ticket -> 
			myPets.add(petServices.findOnePetById(ticket
					.getPet()
					.getId()))
					);
			return myPets;
		}
	
	@Override
	public Ticket createNewTicket(PetDTO petDTO, TokenDTO tokeninfo) {
		Token token = tokenRepository.findOneByContent(tokeninfo.getToken());
		
		User user = token.getUser();
		Pet pet = petServices.finOnePetByName(petDTO);
		
		Timestamp time = new Timestamp(System.currentTimeMillis());
		
		Ticket ticket = new Ticket(pet, user, time);
		
		return ticket;
	}
	
	@Override
	public List<Ticket> findAllMyTickets() {
		User user = findOneUserByName(getUserName());
		return ticketRepository.findByUser(user);
	}
	
	@Override
	public void insertTicket(Ticket ticket) {
		ticketRepository.save(ticket);
	}
	
	
	@Override
	public Ticket findOneTicketById(int id) {
		return ticketRepository.findOneById(id);
	}
	
	
	@Override
	public void deleteOneTicket(Ticket ticket) {
		ticketRepository.delete(ticket);
	}
	
	@Override
	public User findOneUserByName(String name) {
		return userRepository.findOneByName(name);
	}
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public Boolean isTokenValid(User user, String token){
		
		cleanTokens(user);
		List<Token> tokens = tokenRepository.findByUserAndActive(user, true);
		
		return tokens
				.stream()
				.filter((userToken) -> { return userToken.getContent().equals(token) && userToken.getActive();})
				.findAny()
				.orElse(null) != null;
	}
	
	@Transactional(rollbackOn = Exception.class)
	private void cleanTokens(User user) {
		List<Token> tokens = tokenRepository.findByUserAndActive(user, true);
		
		tokens.forEach((userToken) -> 
		{
			if(!tokenManager.validateJwtToken(userToken.getContent(), user.getName())) {
				userToken.setActive(false);
				tokenRepository.save(userToken);
			}
		});
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void insertToken(User user, String token) {
			cleanTokens(user);
			 Token newToken = new Token(token, user);
			 tokenRepository.save(newToken);
	}
	
	@Override
	public boolean comparePassoword(User user, String passsoword) {
		return encoder.matches(passsoword, user.getPassword());
	}

	@Override
	public boolean isAdmin() {
		String authName = getUserName();
		User user = userRepository.findOneByName(authName);
		
		if(user.getAdmin()) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<Status> findAllStatusByAvailable() {
		return statusRepository.findByState("Homeless");
	}
	
}

