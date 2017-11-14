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

import com.poliMobile.bussines.ChatBL;

@RestController
public class ChatController {
	
	@Autowired
	ChatBL chat=new ChatBL();
	
	@GetMapping("/chat/{requestID}&{userID}&{partnerID}")
	public ResponseEntity getChats(@PathVariable("requestID") int requestID, @PathVariable("userID") int userID,
			@PathVariable("partnerID") int partnerID){
		try{
			return new ResponseEntity(chat.getChats(requestID, userID, partnerID),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/chat/add")
	public ResponseEntity addChat(@RequestBody Map<String, Object> params){
		try{
			return new ResponseEntity(chat.addChat(params),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/chat/remove/{id}")
	public ResponseEntity removeChat(@PathVariable("id") int id){
		try{
			return new ResponseEntity(chat.removeChat(id),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
}
