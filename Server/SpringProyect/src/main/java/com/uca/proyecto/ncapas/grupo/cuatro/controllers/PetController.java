package com.uca.proyecto.ncapas.grupo.cuatro.controllers;

import java.util.ArrayList;
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

import com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs.PetDTO;
import com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs.ResponseMessageDTO;
import com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs.TokenDTO;
import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Pet;
import com.uca.proyecto.ncapas.grupo.cuatro.services.PetServices;
import com.uca.proyecto.ncapas.grupo.cuatro.services.UserServices;

@RestController()
@RequestMapping("/petsv/pet")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true" )
public class PetController {

	@Autowired
	private UserServices userServices;
	
	@Autowired
	private PetServices petServices;
	
	@PostMapping("/add")
	public ResponseEntity<ResponseMessageDTO> addPet(@Valid PetDTO petinfo, BindingResult result){
		try {
			if(result.hasErrors()){
				String Error = result.getAllErrors().toString();
				
				return new ResponseEntity<ResponseMessageDTO>(
						new ResponseMessageDTO("No se pueden dejar campos vacios " + Error),
						HttpStatus.BAD_REQUEST
						);
			}
			
			if(petServices.finOnePetByName(petinfo) != null) {
				return new ResponseEntity<ResponseMessageDTO>(
						new ResponseMessageDTO("Esta mascota ya esta registrada"),
						HttpStatus.BAD_REQUEST
						);
			}
			
			if(petServices.findOneStatusByPet(petinfo) != null) {
				return new ResponseEntity<ResponseMessageDTO>(
						new ResponseMessageDTO("Exite un status para este pet"),
						HttpStatus.INTERNAL_SERVER_ERROR
						);
			}
			
			petServices.insertPet(petinfo);
			Pet pet = petServices.finOnePetByName(petinfo);
			petServices.insertStatus(pet);
			
			return new ResponseEntity<ResponseMessageDTO>(
					new ResponseMessageDTO("Sucessfull"),
					HttpStatus.OK
					);
		} catch (Exception e) {
			
			String ERr = e.getMessage();
			return new ResponseEntity<ResponseMessageDTO>(
					new ResponseMessageDTO("error"  + ERr),
					HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Pet>> findAllPet(){
		try {
			return new ResponseEntity<List<Pet>>(
					petServices.findAll(),
					HttpStatus.OK
					);
		} catch (Exception e) {
			return new ResponseEntity<>(
					null,
					HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@GetMapping("/findAllAvailable")
	public ResponseEntity<List<Pet>> findAllPetAvailable(){
		try {
			
			List<Integer> idPetavailable = new ArrayList<>();
			
			userServices.findAllStatusByAvailable().forEach(
					status -> idPetavailable.add(status.getId()) // TODO: ask WTFF
					);
			
			List<Pet> petAvailable = new ArrayList<>();
			
			idPetavailable.forEach(idPet -> 
				petAvailable.add(petServices.findOnePetById(idPet))
			);
			
			return new ResponseEntity<>(
					petAvailable,
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
	public ResponseEntity<ResponseMessageDTO> deletePet(@PathVariable String name){
		try {
			
			PetDTO pet = new PetDTO();
			pet.setName(name);
			
			if(petServices.finOnePetByName(pet) == null) {
				return new ResponseEntity<ResponseMessageDTO>(
						new ResponseMessageDTO("Esta mascota no exite"),
						HttpStatus.BAD_REQUEST
						);
			}
			
			/* if(!userServices.isAdmin()) {
				return new ResponseEntity<ResponseMessageDTO>(
						new ResponseMessageDTO("sin autorizacion"),
						HttpStatus.UNAUTHORIZED
						);
			}*/
			
			PetDTO petDto = new PetDTO();
			
			petDto.setName(name);
			
			if(petServices.findOneStatusByPet(pet) == null) {
				return new ResponseEntity<ResponseMessageDTO>(
						new ResponseMessageDTO("No exite status para este pet"),
						HttpStatus.BAD_REQUEST
						);
			}			
			
			petServices.deleteStatusFromPet(pet);
			
			petServices.DeletePet(pet);
			
			return new ResponseEntity<ResponseMessageDTO>(
					new ResponseMessageDTO("Se ha eliminado la mascota"),
					HttpStatus.OK
					);
			
		} catch (Exception e) {
			String er = e.getMessage();
			return new ResponseEntity<ResponseMessageDTO>(
					new ResponseMessageDTO("error " + er),
					HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	
	@GetMapping("/findAllAdoptedByMe")
	public ResponseEntity<List<Pet>> findAllPetAdoptedByMe(){
		try {
			return new ResponseEntity<List<Pet>>(
					userServices.findAllPetAdoptedByMe(),
					HttpStatus.OK
					);
		} catch (Exception e) {
			return new ResponseEntity<>(
					null,
					HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@GetMapping("/findAllAdoptedByMe2")
	public ResponseEntity<List<Pet>> findAllPetAdoptedByMe2(TokenDTO token){
		try {
			return new ResponseEntity<List<Pet>>(
					userServices.findAllPetAdoptedByMe2(token),
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
