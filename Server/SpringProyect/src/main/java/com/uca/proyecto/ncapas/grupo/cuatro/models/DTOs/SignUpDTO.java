package com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs;

import javax.validation.constraints.NotBlank;

public class SignUpDTO {
	
	@NotBlank
	private String name;
	
	@NotBlank
	
	private String email;
	
	@NotBlank
	
	private String password;
	
	
	private String phonenumber;


	protected SignUpDTO(@NotBlank String name,
			String email,
			String password,
			String phonenumber) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phonenumber = phonenumber;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPhonenumber() {
		return phonenumber;
	}


	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}



	

}
