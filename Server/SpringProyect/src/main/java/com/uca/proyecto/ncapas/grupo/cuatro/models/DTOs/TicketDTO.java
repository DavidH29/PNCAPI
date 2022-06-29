package com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs;

import java.sql.Timestamp;

public class TicketDTO {
	
	private int id;
	private String id_user;
	private String id_pet;
	private Timestamp timeStamp;
	
	public TicketDTO(int id, String id_user, String id_pet, Timestamp timeStamp) {
		super();
		this.id = id;
		this.id_user = id_user;
		this.id_pet = id_pet;
		this.timeStamp = timeStamp;
	}
	
	public TicketDTO() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getId_user() {
		return id_user;
	}
	public void setId_user(String id_user) {
		this.id_user = id_user;
	}
	public String getId_pet() {
		return id_pet;
	}
	public void setId_pet(String id_pet) {
		this.id_pet = id_pet;
	}
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	

}
