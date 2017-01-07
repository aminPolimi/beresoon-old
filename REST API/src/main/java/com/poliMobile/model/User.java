package com.poliMobile.model;

import org.springframework.stereotype.Component;

@Component
public class User {

	String userName;
	String password;
	
	public String getUserName(){
		return userName;
	}
	
	public String getPassword(){
		return password;
	}
}
