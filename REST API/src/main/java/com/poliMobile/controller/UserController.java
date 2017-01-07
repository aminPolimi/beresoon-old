package com.poliMobile.controller;

import java.util.List;

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

import com.poliMobile.model.*;

@RestController
public class UserController {

	
	@Autowired
	private User usr;

	
	@GetMapping("/login/{userName}&{password}")
	public ResponseEntity login(@PathVariable("userName") String un, @PathVariable("password") String pass) {
		try{
		UserModel um = new UserModel();
		String res = um.login(un, pass);
		return new ResponseEntity(res,HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PostMapping("/signup")
	public ResponseEntity signup(@RequestBody User user){
		
		return new ResponseEntity(user,HttpStatus.ACCEPTED);
	}

}
