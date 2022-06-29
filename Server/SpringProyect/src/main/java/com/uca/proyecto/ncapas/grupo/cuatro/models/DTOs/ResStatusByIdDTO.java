package com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs;

import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Pet;
import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Status;

public class ResStatusByIdDTO {
	
	private Pet pet;
	private Status status;
	
	public ResStatusByIdDTO(Pet pet, Status status) {
		super();
		this.pet = pet;
		this.status = status;
	}
	
	public ResStatusByIdDTO() {
		super();
	}
	public Pet getPet() {
		return pet;
	}
	public void setPet(Pet pet) {
		this.pet = pet;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	
}
