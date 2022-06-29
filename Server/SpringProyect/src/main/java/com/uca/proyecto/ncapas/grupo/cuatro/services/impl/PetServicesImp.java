package com.uca.proyecto.ncapas.grupo.cuatro.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs.PetDTO;
import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Pet;
import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Status;
import com.uca.proyecto.ncapas.grupo.cuatro.repositories.PetRepository;
import com.uca.proyecto.ncapas.grupo.cuatro.repositories.StatusRepository;
import com.uca.proyecto.ncapas.grupo.cuatro.services.PetServices;

@Service
public class PetServicesImp implements PetServices{
	
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private StatusRepository statusRepository;
	
	@Override
	public List<Pet> findAll() {
		return petRepository.findAll();
	}
	
	@Override
	public Pet finOnePetByName(PetDTO petinfo) {
		Pet pet = petRepository.findOneByName(petinfo.getName());
		
		if(pet == null) {
			return null;
		}
		
		return pet;
	}
	
	@Override
	public Pet findOnePetById(int id) {
		Pet pet = petRepository.findOneById(id);
		return pet;
	}
	
	@Override
	public void insertPet(PetDTO petinfo) {
		Pet pet = new Pet();
		pet.setName(petinfo.getName());
		pet.setType(petinfo.getType());
		pet.setRace(petinfo.getRace());
		pet.setSize(petinfo.getSize());
		pet.setGender(petinfo.getGender());
		pet.setPlate(petinfo.getPlate());
		pet.setColor(petinfo.getColor());
		pet.setAge(Integer.parseInt(petinfo.getAge()));
		pet.setObservations(petinfo.getObservations());
		
		petRepository.save(pet);
		
	}
	
	
	@Override
	public void DeletePet(PetDTO petinfo) {
		Pet pet = finOnePetByName(petinfo);
		
		petRepository.delete(pet);
	}
	
	@Override
	public Status findOneStatusByPet(PetDTO petinfo) {
		Pet pet = finOnePetByName(petinfo);
		
		if(pet == null) {
			return null;
		}
		
		Status status = statusRepository.findOneByPet(pet);
		return status;
	}
	
	
	@Override
	public void insertStatus(Pet pet) {
		Status status = new Status("Homeless", pet);
		statusRepository.save(status);
	}
	
	
	@Override
	public void deleteStatusFromPet(PetDTO petinfo) {
		Pet pet = finOnePetByName(petinfo);
		Status status = statusRepository.findOneByPet(pet);
		statusRepository.delete(status);
	}
	
	@Override
	public void changeStatus(PetDTO petDTO, String adopted) {
		Pet pet = finOnePetByName(petDTO);
		Status status = statusRepository.findOneByPet(pet);
		status.setState(adopted);
		statusRepository.save(status);
		
	}
}
