package com.uca.proyecto.ncapas.grupo.cuatro.services;

import java.util.List;

import com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs.VolunteerDTO;
import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Volunteer;

public interface VolunteerServices {

	void insertVolunteer(VolunteerDTO volunteerInfo);
	Volunteer findOneVolunteerByName(String name);
	void deleteVolunteerByName(String name);
	List<Volunteer> findAllVolunteers();
}
