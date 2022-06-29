package com.uca.proyecto.ncapas.grupo.cuatro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs.PetDTO;
import com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs.ResponseMessageDTO;
import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Pet;
import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Ticket;
import com.uca.proyecto.ncapas.grupo.cuatro.services.PetServices;
import com.uca.proyecto.ncapas.grupo.cuatro.services.UserServices;

@RestController()
@RequestMapping("/petsv/ticket")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true" )
public class TicketController {
	
	@Autowired
	private UserServices userServices;
	
	@Autowired
	private PetServices petServices;
	
	
	@PostMapping("/delete/id={id}")
	public ResponseEntity<ResponseMessageDTO> deleteTicket(@PathVariable int id){
		try {
			
			if(!userServices.isAdmin()) {
				return new ResponseEntity<ResponseMessageDTO>(
						new ResponseMessageDTO("No tienes permisos"),
						HttpStatus.UNAUTHORIZED
						);
			}
			
			if(userServices.findOneTicketById(id) == null) {
				return new ResponseEntity<ResponseMessageDTO>(
						new ResponseMessageDTO("Este ticket no existe"),
						HttpStatus.BAD_REQUEST
						);
			}
			
			Ticket ticket = userServices.findOneTicketById(id);
			Pet pet = petServices.findOnePetById(ticket.getPet().getId());
			PetDTO petDto = new PetDTO();
			petDto.setName(pet.getName());
			petServices.changeStatus(petDto, "homeless");
			
			userServices.deleteOneTicket(ticket);
			
			return new ResponseEntity<ResponseMessageDTO>(
					new ResponseMessageDTO("Se ha cancelado el proceso de adopcion"),
					HttpStatus.OK
					);
			
		} catch (Exception e) {
			return new ResponseEntity<ResponseMessageDTO>(
					new ResponseMessageDTO("Error"),
					HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	//TODO WTF
	@GetMapping("/findAllMyTickets")
	public ResponseEntity<List<Ticket>> mytickets(){
		try {
			userServices.findAllMyTickets().forEach(ticket -> System.out.println("El id ticket es " + ticket.getId()));
			
			return new ResponseEntity<List<Ticket>>(
					userServices.findAllMyTickets(),
					HttpStatus.ACCEPTED
					);
			
		} catch (Exception e) {
			return new ResponseEntity<>(
					null,
					HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}

}
