package com.poliMobile.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poliMobile.bussines.*;
import com.poliMobile.model.AuthenticationModel;

@RestController
public class UserController {
	
	@Autowired
	private UserBL usr=new UserBL();

	
	@GetMapping("/login/{code}")
	public ResponseEntity login(@PathVariable("code") String code) {
		System.out.println("in login");
		try{
			return new ResponseEntity(usr.login(code),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/logout/{email}&{token}")
	public ResponseEntity logout(@PathVariable("email") String email, @PathVariable("password") String token) {
		System.out.println("in login");
		try{
			AuthenticationModel auth = new AuthenticationModel();
			return new ResponseEntity(auth.deleteToken(email, token),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity getUser(@PathVariable("id") int id){
		try{
			return new ResponseEntity(usr.getUser(id),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/signup")
	public ResponseEntity signup(@RequestBody Map<String,Object> user){
		try{
			return new ResponseEntity(usr.addUser(user),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/user/update")
	public ResponseEntity updateUser(@RequestBody Map<String,Object> user){
		try{
			return new ResponseEntity(usr.updateUser(user),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

}
