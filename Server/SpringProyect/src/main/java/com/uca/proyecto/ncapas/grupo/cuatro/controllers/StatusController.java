package com.uca.proyecto.ncapas.grupo.cuatro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs.PetDTO;
import com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs.ResStatusByIdDTO;
import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Pet;
import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Status;
import com.uca.proyecto.ncapas.grupo.cuatro.services.PetServices;

@RestController()
@RequestMapping("/petsv/status")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true" )
public class StatusController {
	
	@Autowired
	private PetServices petServices;
	
	
	@GetMapping("/findById/id={id}")
	public ResponseEntity<ResStatusByIdDTO> statusById(@PathVariable Integer id) {
		try {
			Pet pet = petServices.findOnePetById(id);
			PetDTO petDto = new PetDTO();
			petDto.setName(pet.getName());
			Status status = petServices.findOneStatusByPet(petDto);
			
			ResStatusByIdDTO res = new ResStatusByIdDTO();
			res.setPet(pet);
			res.setStatus(status);
			
			return new ResponseEntity<ResStatusByIdDTO>(
					res,
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
