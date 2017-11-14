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

import com.poliMobile.bussines.DiscountBL;
import com.poliMobile.bussines.OffersBL;

@RestController
public class OfferController {

	@Autowired
	OffersBL off;
	
	@GetMapping("/offer/{requestsID}")
	public ResponseEntity getOffers(@PathVariable("requestsID") int reqID){
		try{
			off = new OffersBL();
			return new ResponseEntity(off.getOffers(reqID),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/offer/add")
	public ResponseEntity addOffer(@RequestBody Map<String, Object> params){
		try{
			off = new OffersBL();
			return new ResponseEntity(off.addOffer(params),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/offer/remove/{id}")
	public ResponseEntity removeOffer(@PathVariable("id") int id){
		try{
			off = new OffersBL();
			return new ResponseEntity(off.removeOffer(id),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
}
