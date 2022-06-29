package com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs;

import javax.validation.constraints.NotBlank;

public class VolunteerDTO {
	
	@NotBlank
	private String name;
	
	private Integer campaign_id;

	public VolunteerDTO() {
		super();
	}

	public VolunteerDTO(@NotBlank String name, Integer campaign_id) {
		super();
		this.name = name;
		this.campaign_id = campaign_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(Integer campaign_id) {
		this.campaign_id = campaign_id;
	}

	
	
}
