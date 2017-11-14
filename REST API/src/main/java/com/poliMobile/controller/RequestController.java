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

import com.poliMobile.bussines.RequestBL;

@RestController
public class RequestController {
	
	@Autowired
	RequestBL req=new RequestBL();
	
	@GetMapping("/requests/{userID}&{reqID}")
	public ResponseEntity getRequests(@PathVariable("userID") int userID, @PathVariable("reqID") int reqID){
		try{
			return new ResponseEntity(req.getRequests(reqID, userID),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/request/add")
	public ResponseEntity addRequest(@RequestBody Map<String,Object> params){
		try{
			return new ResponseEntity(req.addRequest(params),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/request/remove/{id}")
	public ResponseEntity removeRequest(@PathVariable("id") int id){
		try{
			return new ResponseEntity(req.removeRequest(id),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
		}
	}

}
