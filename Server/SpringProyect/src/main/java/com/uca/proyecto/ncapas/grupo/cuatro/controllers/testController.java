package com.uca.proyecto.ncapas.grupo.cuatro.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs.ResponseMessageDTO;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true" )
public class testController {
	
	@GetMapping("/")
	public ResponseEntity<ResponseMessageDTO> hellowWorld(){
		return new ResponseEntity<ResponseMessageDTO>(
				new ResponseMessageDTO("Hola mundito"),
				HttpStatus.OK
				);
	}
	
}
