package com.uca.proyecto.ncapas.grupo.cuatro.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity(name = "pet")
public class Pet {
	
	@Id
	@SequenceGenerator(name = "pet_id_seq", sequenceName = "pet_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pet_id_seq")
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "race")
	private String race;
	
	@Column(name = "size")
	private String size;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "plate")
	private String plate;
	
	@Column(name = "color")
	private String color;
	
	@Column(name = "age")
	private Integer age;
	
	@Column(name = "observations")
	private String observations;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_campaign", nullable = true)
	private Campaign campaign;

	public Pet(String name, String type, String race, String size, String gender, String plate, String color,
			Integer age, String observations, Campaign campaign) {
		super();
		this.name = name;
		this.type = type;
		this.race = race;
		this.size = size;
		this.gender = gender;
		this.plate = plate;
		this.color = color;
		this.age = age;
		this.observations = observations;
		this.campaign = campaign;
	}

	public Pet() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

	
	
	
	
}
