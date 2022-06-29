package com.uca.proyecto.ncapas.grupo.cuatro.services;

import java.util.List;

import com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs.CampignDTO;
import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Campaign;

public interface CampaignServices {
	
	Campaign findOneCampaignByName(CampignDTO campaignInfo);
	Campaign finOneCampaignByID(Integer id);
	void insertNewCampaig(CampignDTO campaignInfo);
	void deleteCampaign(String name);
	void changeRequestvalueCampaign(Campaign campaing);
	List<Campaign> findAllCampaigns();
}
