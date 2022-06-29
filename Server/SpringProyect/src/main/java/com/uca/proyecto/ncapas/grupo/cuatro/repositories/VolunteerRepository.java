package com.uca.proyecto.ncapas.grupo.cuatro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Volunteer;


public interface VolunteerRepository extends JpaRepository<Volunteer, Integer> {

	Volunteer findOneByName(String name);
}
