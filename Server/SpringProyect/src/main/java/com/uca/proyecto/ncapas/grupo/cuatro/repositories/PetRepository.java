package com.uca.proyecto.ncapas.grupo.cuatro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Pet;

public interface PetRepository extends JpaRepository<Pet, Integer>{
	Pet findOneByName(String name);
	Pet findOneById(int id);
}
