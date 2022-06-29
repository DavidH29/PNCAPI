package com.uca.proyecto.ncapas.grupo.cuatro.utils;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.User;
import com.uca.proyecto.ncapas.grupo.cuatro.services.UserServices;
import com.uca.proyecto.ncapas.grupo.cuatro.services.impl.authUserDetailsServiceImp;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	private authUserDetailsServiceImp userDetailsService;
	
	@Autowired
	private TokenManager tokenManager;
	
	@Autowired
	private UserServices userServices;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String tokenHeader = request.getHeader("Authorization");
		String username = null;
		String token = null;
		
		if(tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
			token = tokenHeader.substring(7);
			
			try {
				System.out.println(tokenHeader);
				username = tokenManager.getUsernameFromToken(token);
				
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token Expired");
			} catch (MalformedJwtException e) {
				System.out.println("JTW malformed");
			}
		}else {
			System.out.println("bearer not found");
		}
	
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			try {
				UserDetails userDetailes = userDetailsService.loadUserByUsername(username);
				User userFound = userServices.findOneUserByName(username);
				
				if(userFound != null) {
					boolean isTokenRegistered = userServices.isTokenValid(userFound, token);
					
					if(isTokenRegistered) {
						UsernamePasswordAuthenticationToken authToken =  
								new UsernamePasswordAuthenticationToken(userDetailes,null, userDetailes.getAuthorities());
						
						authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						
						SecurityContextHolder
						.getContext()
						.setAuthentication(authToken);
					}
				} 
			} catch (Exception e) {
				System.err.print(e);
				System.out.println("Error in token verification");
			}
		}
		
		filterChain.doFilter(request, response);
	}
	
	
	
	
}
