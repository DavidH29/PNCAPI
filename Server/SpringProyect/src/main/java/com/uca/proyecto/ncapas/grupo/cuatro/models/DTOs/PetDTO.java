package com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs;

import javax.validation.constraints.NotBlank;

public class PetDTO {
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String type;
	
	@NotBlank
	private String race;
	
	@NotBlank
	private String size;
	
	@NotBlank
	private String gender;
	
	@NotBlank
	private String color;
	
	@NotBlank
	private String age;
	
	@NotBlank
	private String plate;
	
	@NotBlank
	private String observations;
	
	@NotBlank
	private String date;

	
	public PetDTO() {
		super();
	}


	protected PetDTO(@NotBlank String name, @NotBlank String type, @NotBlank String race, @NotBlank String size,
			@NotBlank String gender, @NotBlank String color, @NotBlank String age, @NotBlank String plate,
			@NotBlank String observations, @NotBlank String date) {
		super();
		this.name = name;
		this.type = type;
		this.race = race;
		this.size = size;
		this.gender = gender;
		this.color = color;
		this.age = age;
		this.plate = plate;
		this.observations = observations;
		this.date = date;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getRace() {
		return race;
	}


	public void setRace(String race) {
		this.race = race;
	}


	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public String getPlate() {
		return plate;
	}


	public void setPlate(String plate) {
		this.plate = plate;
	}


	public String getObservations() {
		return observations;
	}


	public void setObservations(String observations) {
		this.observations = observations;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	
	
}
