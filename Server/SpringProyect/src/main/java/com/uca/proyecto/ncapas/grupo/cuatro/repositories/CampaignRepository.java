package com.uca.proyecto.ncapas.grupo.cuatro.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Campaign;


public interface CampaignRepository extends JpaRepository<Campaign, Integer> {
	Campaign findOneByName(String name);
	Campaign findOneById(int id);
}
