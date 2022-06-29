package com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs;

public class CampignDTO {
	private String name;
	private String date;
	private String description;
	private String address;
	
	public CampignDTO(String name, String date, String description, String address) {
		super();
		this.name = name;
		this.date = date;
		this.description = description;
		this.address = address;
	}
	public CampignDTO() {
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
