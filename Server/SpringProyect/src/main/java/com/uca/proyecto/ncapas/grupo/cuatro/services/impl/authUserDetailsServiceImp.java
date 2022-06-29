package com.uca.proyecto.ncapas.grupo.cuatro.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.User;
import com.uca.proyecto.ncapas.grupo.cuatro.services.UserServices;

@Service
public class authUserDetailsServiceImp implements UserDetailsService{
	
	@Autowired
	UserServices userServices;
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		try {
			User userFound = userServices.findOneUserByName(name);
			
			if(userFound != null) {
			return new org.springframework.security.core.userdetails.User(
					userFound.getName(),
					userFound.getPassword(),
					new ArrayList<>()
					);
			}else {
				throw new UsernameNotFoundException("usuario: " + name + "no encontrado");
			}
			
		} catch (Exception e) {
			throw new UsernameNotFoundException("usuario: " + name + "no encontrado");
		}
	}
 
	
}
