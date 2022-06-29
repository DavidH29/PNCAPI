package com.uca.proyecto.ncapas.grupo.cuatro.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs.CampignDTO;
import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Campaign;
import com.uca.proyecto.ncapas.grupo.cuatro.repositories.CampaignRepository;
import com.uca.proyecto.ncapas.grupo.cuatro.services.CampaignServices;

@Service
public class CampaignServicesImp implements CampaignServices {
	
	@Autowired
	private CampaignRepository campaignRepository;
	
	@Override
	public Campaign findOneCampaignByName(CampignDTO campaignInfo) {
		
		Campaign campaign = campaignRepository.findOneByName(campaignInfo.getName());
		
		 return campaign;
	}
	
	public Timestamp ParseDateToTimestamp(CampignDTO campaignInfo) {
		String[] splitedDateDDMMYY = campaignInfo.getDate().split("-");
	
		@SuppressWarnings("deprecation")
		Date date = new Date(0,0,0);
		date.setDate(Integer.parseInt(splitedDateDDMMYY[2]));
		date.setYear(Integer.parseInt(splitedDateDDMMYY[0]) - 1900);
		date.setMonth(Integer.parseInt(splitedDateDDMMYY[1]) - 1);
		
		@SuppressWarnings("deprecation")
		Timestamp timeStamp = new Timestamp(date.getTime());
		return timeStamp;
	}

	@Override
	public void insertNewCampaig(CampignDTO campaignInfo) {
		
		Timestamp timeStamp = ParseDateToTimestamp(campaignInfo);
		
		Campaign campaign = new Campaign();
		
		campaign.setName(campaignInfo.getName());
		campaign.setDate(timeStamp);
		campaign.setDeparment(campaignInfo.getAddress());
		campaign.setDescription(campaignInfo.getDescription());
		campaign.setRequest(true);
		
		campaignRepository.save(campaign);
	}
	
	
	@Override
	public Campaign finOneCampaignByID(Integer id) {
		
		return campaignRepository.findOneById(id);
	}
	

	@Override
	public void changeRequestvalueCampaign(Campaign campaing) {
		campaing.setRequest(false);
		campaignRepository.save(campaing);
	}
	
	@Override
	public void deleteCampaign(String name) {
		 Campaign Campaign = campaignRepository.findOneByName(name);
		 campaignRepository.delete(Campaign);
	}
	
	@Override
	public List<Campaign> findAllCampaigns() {
		return campaignRepository.findAll();
	}

}
