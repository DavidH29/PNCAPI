package com.uca.proyecto.ncapas.grupo.cuatro.services;

import java.util.List;

import com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs.PetDTO;
import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Pet;
import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Status;

public interface PetServices {
	
	Pet finOnePetByName(PetDTO petinfo);
	Pet findOnePetById(int id);
	void insertPet(PetDTO petinfo);
	void DeletePet(PetDTO petinfo);
	Status findOneStatusByPet(PetDTO petinfo);
	void insertStatus(Pet pet);
	void deleteStatusFromPet(PetDTO petinfo);
	void changeStatus(PetDTO petDTO, String status);
	List<Pet> findAll();

}
