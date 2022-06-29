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

@Entity(name = "status")
public class Status {
	
	@Id
	@SequenceGenerator(name = "status_id_seq", sequenceName = "status_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "status_id_seq")
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "state")
	private String state;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_pet", nullable = false)
	private Pet pet;

	public Status(String state, Pet pet) {
		super();
		this.state = state;
		this.pet = pet;
	}

	protected Status() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}
	
	

}
