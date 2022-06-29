package com.uca.proyecto.ncapas.grupo.cuatro.models.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "campaign")
public class Campaign {
	
	@Id
	@SequenceGenerator(name = "campaign_id_seq", sequenceName = "campaign_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "campaign_id_seq")
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "date")
	private Timestamp date;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "address")
	private String deparment;
	
	@Column(name = "request")
	private Boolean request;
	
	public Campaign(String name, Timestamp date, String description, String deparment, Boolean request) {
		super();
		this.name = name;
		this.date = date;
		this.description = description;
		this.deparment = deparment;
		this.request = request;
	}

	public Campaign() {
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

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDeparment() {
		return deparment;
	}

	public void setDeparment(String deparment) {
		this.deparment = deparment;
	}

	public Boolean getRequest() {
		return request;
	}

	public void setRequest(Boolean request) {
		this.request = request;
	}

	
}
