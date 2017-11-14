package com.poliMobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.poliMobile.bussines.CityBL;
import com.poliMobile.model.CityModel;

@RestController

public class CityController {

	@Autowired
	CityBL city=new CityBL();
	
	
	@GetMapping("/city/{countryID}")
	public ResponseEntity getCity(@PathVariable("countryID") int countryID){
		try{
			return new ResponseEntity(city.getCities(countryID),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/countries")
	public ResponseEntity getCountries(){
		try{
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		      String name = auth.getName(); //get logged in username
//			System.out.println(name);
			
			return new ResponseEntity(city.getCountries(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
}
