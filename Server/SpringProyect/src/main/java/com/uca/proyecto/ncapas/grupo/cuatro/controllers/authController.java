package com.uca.proyecto.ncapas.grupo.cuatro.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs.LoginDTO;
import com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs.SignUpDTO;
import com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs.ResponseMessageDTO;
import com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs.TokenDTO;
import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.User;
import com.uca.proyecto.ncapas.grupo.cuatro.services.UserServices;
import com.uca.proyecto.ncapas.grupo.cuatro.utils.TokenManager;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true" )

public class authController {
	
	
	@Autowired
	private UserServices userServices;
	
	@Autowired
	private TokenManager tokenManager;
	
	@PostMapping("/signup")
	public ResponseEntity<ResponseMessageDTO> registerUser( @Valid SignUpDTO userInfo, BindingResult result){
		try {
			
			System.out.println("el nombre es " + userInfo.getName());
			System.out.println("el nombre es " + userInfo.getEmail());
			System.out.println("el nombre es " + userInfo.getPassword());
			System.out.println("el nombre es " + userInfo.getPhonenumber());
			
			if(result.hasErrors()) {
				
				String errores = result.getAllErrors().toString();
				return new ResponseEntity<>(
						new ResponseMessageDTO("hay errores en: " + errores),
						HttpStatus.CONFLICT
						);
			}
			
			if(userServices.findOneUserByEmail(userInfo.getEmail()) != null) {
				return new ResponseEntity<>(
						new ResponseMessageDTO("Este usuario ya existe"),
						HttpStatus.CONFLICT
						);
			}
			
			userServices.insertNewUser(userInfo, false);
			
			return new ResponseEntity<>(
					new ResponseMessageDTO(
							"succesfull"),
							HttpStatus.OK
					);
			
		} catch (Exception e) {
			String error = e.getMessage().toString();
			return new ResponseEntity<>(
					new ResponseMessageDTO("Fallo en" + error),
					HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/asda8sda876sda78s6d6as7d687a6sd78a7s6d7a7sd")
	public ResponseEntity<ResponseMessageDTO> registerAdmin( @Valid SignUpDTO userInfo, BindingResult result){
		try {
			
			System.out.println("el nombre es " + userInfo.getName());
			System.out.println("el nombre es " + userInfo.getEmail());
			System.out.println("el nombre es " + userInfo.getPassword());
			System.out.println("el nombre es " + userInfo.getPhonenumber());
			
			if(result.hasErrors()) {
				
				String errores = result.getAllErrors().toString();
				return new ResponseEntity<>(
						new ResponseMessageDTO("hay errores en: " + errores),
						HttpStatus.CONFLICT
						);
			}
			
			if(userServices.findOneUserByEmail(userInfo.getEmail()) != null) {
				return new ResponseEntity<>(
						new ResponseMessageDTO("Este usuario ya existe"),
						HttpStatus.CONFLICT
						);
			}
			
			userServices.insertNewUser(userInfo, true);
			
			return new ResponseEntity<>(
					new ResponseMessageDTO(
							"succesfull"),
							HttpStatus.OK
					);
			
		} catch (Exception e) {
			String error = e.getMessage().toString();
			return new ResponseEntity<>(
					new ResponseMessageDTO("Fallo en" + error),
					HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/singin")
	public ResponseEntity<TokenDTO> login (@Valid LoginDTO userInfo, BindingResult result ){
			try {
				
				if(result.hasErrors()) {
					
					return new ResponseEntity<TokenDTO>(
							new TokenDTO(),
							HttpStatus.CONFLICT
							);
				}
				
				User user = userServices.findOneUserByName(userInfo.getName());
				
				if(user == null) {
					return new ResponseEntity<TokenDTO>(
							new TokenDTO(),
							HttpStatus.BAD_REQUEST);
				}
				
				if(!userServices.comparePassoword(user, userInfo.getPassword())) {
					return new ResponseEntity<TokenDTO>(
							new TokenDTO(),
							HttpStatus.UNAUTHORIZED
							);
				}
				
				final String token = tokenManager.generateJwtTokens(user.getName());
				
				userServices.insertToken(user, token);
				
				return new ResponseEntity<TokenDTO>(
						new TokenDTO(token),
						HttpStatus.CREATED
						);
				
			} catch (Exception e) {
				return new ResponseEntity<TokenDTO>(
						new TokenDTO(),
						HttpStatus.INTERNAL_SERVER_ERROR
						);
			}
		}
	
	@GetMapping("/isAdmin")
	public ResponseEntity<Boolean> isAdmin(){
		try {
			return new ResponseEntity<Boolean>(
					userServices.isAdmin(),
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
