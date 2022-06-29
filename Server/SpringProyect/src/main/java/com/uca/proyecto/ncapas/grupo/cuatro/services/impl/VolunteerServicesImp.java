package com.uca.proyecto.ncapas.grupo.cuatro.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs.VolunteerDTO;
import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Campaign;
import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Volunteer;
import com.uca.proyecto.ncapas.grupo.cuatro.repositories.CampaignRepository;
import com.uca.proyecto.ncapas.grupo.cuatro.repositories.VolunteerRepository;
import com.uca.proyecto.ncapas.grupo.cuatro.services.VolunteerServices;

@Service
public class VolunteerServicesImp implements VolunteerServices{
	
	@Autowired
	private VolunteerRepository volunteerRespository;
	
	@Autowired
	private CampaignRepository campaignRepository;
	
	@Override
	public List<Volunteer> findAllVolunteers() {
		return volunteerRespository.findAll();
	}
	
	@Override
	public void insertVolunteer(VolunteerDTO volunteerInfo) {
		
		Volunteer volunteer = new Volunteer();
		
		volunteer.setName(volunteerInfo.getName());
		
		Campaign Campaign = campaignRepository.findOneById(volunteerInfo.getCampaign_id());
		
		volunteer.setCampaign(Campaign);
		
		volunteerRespository.save(volunteer);
	}

	@Override
	public Volunteer findOneVolunteerByName(String name) {
		return volunteerRespository.findOneByName(name);
	}

	@Override
	public void deleteVolunteerByName(String name) {
		Volunteer volunteer = findOneVolunteerByName(name);
		volunteerRespository.delete(volunteer);
	}


}
