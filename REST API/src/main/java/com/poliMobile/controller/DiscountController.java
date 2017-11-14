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

@RestController
public class DiscountController {

	@Autowired
	DiscountBL dis;
	
	@GetMapping("/discount")
	public ResponseEntity getDiscounts(){
		try{
			dis = new DiscountBL();
			return new ResponseEntity(dis.getDiscount(0),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/discount/{id}")
	public ResponseEntity getDiscounts(@PathVariable("id") int id){
		try{
			dis = new DiscountBL();
			return new ResponseEntity(dis.getDiscount(id),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/discount/add")
	public ResponseEntity addDiscounts(@RequestBody Map<String, Object> params){
		try{
			dis = new DiscountBL();
			return new ResponseEntity(dis.addDiscount(params),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/discount/remove/{id}")
	public ResponseEntity addDiscounts(@PathVariable("id") int id){
		try{
			dis = new DiscountBL();
			return new ResponseEntity(dis.removeDiscount(id),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
}
