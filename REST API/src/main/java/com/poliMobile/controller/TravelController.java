package com.poliMobile.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poliMobile.bussines.TravelBL;

@RestController
public class TravelController {
	
	@Autowired
	TravelBL trv = new TravelBL();
	
	@GetMapping("/travelers")
	public ResponseEntity getRequests(){
		try{
			return new ResponseEntity(trv.getTravels(0),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/traveler/{travelID}")
	public ResponseEntity getRequest(@PathVariable("travelID") int travelID){
		try{
			return new ResponseEntity(trv.getTravels(travelID),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/travel/add")
	public ResponseEntity addTravel(@RequestBody Map<String,Object> params){
		try{
			return new ResponseEntity(trv.addTravel(params),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/travel/remove/{id}")
	public ResponseEntity removeTravel(@PathVariable("id") int id){
		try{
			return new ResponseEntity(trv.removeTravel(id),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
		}
	}

}
