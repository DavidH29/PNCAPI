package com.uca.proyecto.ncapas.grupo.cuatro.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class viewController {
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	@GetMapping("/aboutus")
	public String aboutus() {
		return "aboutus";
	}
	@GetMapping("/campaigns")
	public String campaigns() {
		return "campaigns";
	}
	@GetMapping("/main")
	public String main() {
		return "main";
	}
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	@GetMapping("/report")
	public String report() {
		return "report";
	}
	@GetMapping("/information")
	public String information() {
		return "information";
	}
	@GetMapping("/adoptionTicket")
	public String adoptionTicket() {
		return "adoptionTicket";
	}
	
}
