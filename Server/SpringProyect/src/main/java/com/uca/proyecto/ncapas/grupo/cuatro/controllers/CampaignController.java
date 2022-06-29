package com.uca.proyecto.ncapas.grupo.cuatro.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs.CampignDTO;
import com.uca.proyecto.ncapas.grupo.cuatro.models.DTOs.ResponseMessageDTO;
import com.uca.proyecto.ncapas.grupo.cuatro.models.entities.Campaign;
import com.uca.proyecto.ncapas.grupo.cuatro.services.CampaignServices;
import com.uca.proyecto.ncapas.grupo.cuatro.services.UserServices;

@RestController()
@RequestMapping("/petsv/campaign")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true" )

public class CampaignController {
	
	@Autowired
	private CampaignServices campaignServices;
	
	@Autowired
	private UserServices userServices;
	
	
	@PostMapping("/add")
	public ResponseEntity<ResponseMessageDTO> addCampaign (@Valid CampignDTO campaignInfo, BindingResult result){
		try {
			
			if(result.hasErrors()) {
				return new ResponseEntity<ResponseMessageDTO>(
						new ResponseMessageDTO("No se pueden dejar campos vacios"),
						HttpStatus.BAD_REQUEST);
			}
			
			if(campaignServices.findOneCampaignByName(campaignInfo) != null) {
				return new ResponseEntity<ResponseMessageDTO>(
						new ResponseMessageDTO("Esta solicitud para este dia ya existe"),
						HttpStatus.BAD_REQUEST
						);
			}
			
			campaignServices.insertNewCampaig(campaignInfo);
			
			return new ResponseEntity<ResponseMessageDTO>(
					new ResponseMessageDTO("se ha insertado una nueva soliciud de campaña"),
					HttpStatus.OK
					);
			
		} catch (Exception e) {
			return new ResponseEntity<ResponseMessageDTO>(
					new ResponseMessageDTO(e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Campaign>> findAllCampaigns(){
		try {
			return new ResponseEntity<List<Campaign>>(
					campaignServices.findAllCampaigns(),
					HttpStatus.OK
					);
			
		} catch (Exception e) {
			return new ResponseEntity<>(
					null,
					HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@PostMapping("/changeRequestValue/id={id}")
	public ResponseEntity<ResponseMessageDTO> changeRequestValue(@PathVariable Integer id){
		try {
			
			if(!userServices.isAdmin()) {
				return new ResponseEntity<ResponseMessageDTO>(
						new ResponseMessageDTO("sin auntorizacion"),
						HttpStatus.UNAUTHORIZED
						);
			}
			
			Campaign campaign = campaignServices.finOneCampaignByID(id);
			
			if(campaign == null) {
				return new ResponseEntity<ResponseMessageDTO>(
						new ResponseMessageDTO("Esta campaña no exite"),
						HttpStatus.BAD_REQUEST
						);
			}
			
			if(campaign.getRequest().equals(false)) {
				return new ResponseEntity<ResponseMessageDTO>(
						new ResponseMessageDTO("Esta campaña ya es un proyecto a realizar"),
						HttpStatus.BAD_REQUEST
						);
			}
			
			campaignServices.changeRequestvalueCampaign(campaign);
			
			return new ResponseEntity<ResponseMessageDTO>(
					new ResponseMessageDTO("la campaña ha pasado de ser solicitud a proyecto"),
					HttpStatus.OK
					);
			
		} catch (Exception e) {
			
			String error = e.getMessage().toString();
			
			return new ResponseEntity<ResponseMessageDTO>(
					new ResponseMessageDTO("Error " + error),
					HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@PostMapping("/delete/name={name}")
	public ResponseEntity<ResponseMessageDTO> deleteCampaign (@PathVariable String name){
		try {
			
			/*if(!userServices.isAdmin()) {
				return new ResponseEntity<ResponseMessageDTO>(
						new ResponseMessageDTO("sin autorizacion"),
						HttpStatus.UNAUTHORIZED
						);
			}*/
			
			CampignDTO dto = new CampignDTO();

			dto.setName(name);
			
			Campaign campaign = campaignServices.findOneCampaignByName(dto);
			
			if(campaign == null) {
				return new ResponseEntity<ResponseMessageDTO>(
						new ResponseMessageDTO("Esta solicitud para este dia no existe"),
						HttpStatus.BAD_REQUEST
						);
			}
			
			campaignServices.deleteCampaign(name);
			
			return new ResponseEntity<ResponseMessageDTO>(
					new ResponseMessageDTO("se ha eliminado la campaña"),
					HttpStatus.OK
					);
			
		} catch (Exception e) {
			return new ResponseEntity<ResponseMessageDTO>(
					new ResponseMessageDTO("Error"),
					HttpStatus.BAD_REQUEST);
		}
	}
	
}