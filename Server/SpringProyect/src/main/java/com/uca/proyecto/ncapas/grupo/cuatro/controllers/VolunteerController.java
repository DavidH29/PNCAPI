package com.uca.proyecto.ncapas.grupo.cuatro.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs.ResponseMessageDTO;
import com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs.VolunteerDTO;
import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Volunteer;
import com.uca.proyecto.ncapas.grupo.cuatro.services.UserServices;
import com.uca.proyecto.ncapas.grupo.cuatro.services.VolunteerServices;

@RestController
@RequestMapping("/petsv/volunteer")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true" )
public class VolunteerController {
	
	@Autowired
	private UserServices userServices;
	
	@Autowired
	private VolunteerServices volunteerServices;
	
	@PostMapping("/add")
	public ResponseEntity<ResponseMessageDTO> addVolunteer(@Valid VolunteerDTO volunteerInfo, BindingResult result){
		try {
			
			if(result.hasErrors()) {
				
				String err = result.getAllErrors().toString();
				
				return new ResponseEntity<ResponseMessageDTO>(
						new ResponseMessageDTO("Error en validator "+ err),
						HttpStatus.BAD_REQUEST
						);
			}
			
			if(volunteerServices.findOneVolunteerByName(volunteerInfo.getName()) != null ) {
				
				return new ResponseEntity<ResponseMessageDTO>(
						new ResponseMessageDTO("este voluntario ya existe"),
						HttpStatus.BAD_REQUEST
						);
			}
			
			volunteerServices.insertVolunteer(volunteerInfo);
			
			return new ResponseEntity<ResponseMessageDTO>(
					new ResponseMessageDTO("succesfull"),
					HttpStatus.OK
					);
			
		} catch (Exception e) {
			
			String err = e.getMessage();
			
			return new ResponseEntity<ResponseMessageDTO>(
					new ResponseMessageDTO("problema al inserta nuevo usuario " + err),
					HttpStatus.BAD_REQUEST
					);
		}
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Volunteer>> findAllVolunteers(){
		try {
			return new ResponseEntity<List<Volunteer>>(
					volunteerServices.findAllVolunteers(),
					HttpStatus.OK
					);
		} catch (Exception e) {
			return new ResponseEntity<>(
					null,
					HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@PostMapping("/delete/name={name}")
	public ResponseEntity<ResponseMessageDTO> deleteVolunteer(@PathVariable String name){
		try {
			
			if(!userServices.isAdmin()) {
				return new ResponseEntity<ResponseMessageDTO>(
						new ResponseMessageDTO("No tienes permisos de administrador"),
						HttpStatus.UNAUTHORIZED
						);
			}
			
			Volunteer volunteer = volunteerServices.findOneVolunteerByName(name);
			
			if(volunteer == null) {
				return new ResponseEntity<>(
						new ResponseMessageDTO("Este usuario no exite"),
								HttpStatus.BAD_REQUEST
						);
			}
			
			volunteerServices.deleteVolunteerByName(name);
			
			return new ResponseEntity<ResponseMessageDTO>(
					new ResponseMessageDTO("succesfull"),
					HttpStatus.OK
					);
			
			
		} catch (Exception e) {
			return new ResponseEntity<ResponseMessageDTO>(
					new ResponseMessageDTO("Error"),
					HttpStatus.BAD_REQUEST
					);
		}
	}
}
