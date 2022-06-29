package com.uca.proyecto.ncapas.grupo.cuatro.controllers;

import java.sql.Timestamp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs.PetDTO;
import com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs.TicketDTO;
import com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs.TokenDTO;
import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Ticket;
import com.uca.proyecto.ncapas.grupo.cuatro.services.PetServices;
import com.uca.proyecto.ncapas.grupo.cuatro.services.UserServices;

@RestController()
@RequestMapping("/petsv")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true" )
public class PetsSvController {
	
	@Autowired
	private UserServices userServices;
	
	
	@Autowired
	private PetServices petServices;
	
	
	@PostMapping("/newAdoption/name={name}")
	public ResponseEntity<TicketDTO> newAdoption(@PathVariable String name, TokenDTO token){
		try {
			PetDTO pet = new PetDTO();
			pet.setName(name);
			
			if(petServices.finOnePetByName(pet) == null) {
				return new ResponseEntity<>(
						null,
						HttpStatus.BAD_REQUEST
						);
			}
			
			if(petServices.findOneStatusByPet(pet).getState().equals("adopted")) {
				return new ResponseEntity<>(
						null,
						HttpStatus.BAD_REQUEST
						);
			}
			
			
			System.out.println("EL ESTATUSSS  " + petServices.findOneStatusByPet(pet).getState());
			
			Ticket ticket = userServices.createNewTicket(pet, token);
			userServices.insertTicket(ticket);
			petServices.changeStatus(pet, "Adopted");
			
			TicketDTO ResticketDTO = new TicketDTO();
			ResticketDTO.setId(ticket
					.getId());
			
			ResticketDTO.setId_user(userServices
					.findOneUserByName(userServices
					.getUserName())
					.getId()
					.toString());
			
			ResticketDTO.setId_pet(petServices
					.finOnePetByName(pet)
					.getId()
					.toString());
			
			ResticketDTO.setTimeStamp(new Timestamp(System.currentTimeMillis()));
			
			return new ResponseEntity<TicketDTO>(
					ResticketDTO,
					HttpStatus.OK
					);
			
		} catch (Exception e) {
			return new ResponseEntity<>(
					null,
					HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
}
