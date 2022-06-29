package com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs;

import javax.validation.constraints.NotBlank;

public class LoginDTO {
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String password;

	protected LoginDTO(@NotBlank String name, @NotBlank String password) {
		super();
		this.name = name;
		this.password = password;
	}

	protected LoginDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
