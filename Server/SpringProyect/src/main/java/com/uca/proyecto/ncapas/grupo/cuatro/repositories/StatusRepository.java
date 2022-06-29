package com.uca.proyecto.ncapas.grupo.cuatro.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Pet;
import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {
	Status findOneByPet(Pet pet);
	List<Status> findByState(String state);
}
