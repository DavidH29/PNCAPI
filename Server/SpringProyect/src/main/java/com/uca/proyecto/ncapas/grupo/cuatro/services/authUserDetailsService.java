package com.uca.proyecto.ncapas.grupo.cuatro.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface authUserDetailsService {
	
	UserDetails loadUserByUsername(String username);

}
