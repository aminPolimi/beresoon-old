package com.poliMobile.bussines;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poliMobile.model.UserModel;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class UserBL {

	UserModel um ;
	General gen=new General();
	
	public String login(String email, String password)throws Exception {
		um = new UserModel();
		//byte[] pass = Base64.decodeBase64(password.getBytes());
		//return um.login(email,new String(pass));
		String token =  gen.generateToken();
		Boolean res = um.login(email,password, token);
		if (res)
			return token;
		return "";
	}
	
	public String getUser(int id) throws Exception{
		um = new UserModel();
		List<Map<String, Object>> msg = um.getUser(id);
		return new ObjectMapper().writeValueAsString(msg);
	}
	
	public boolean addUser(Map<String, Object> params) throws Exception{
		um = new UserModel();
		System.out.println("bef");
		//byte[] pass = Base64.decodeBase64(password.getBytes());
		return 	um.addUser(params.get("firstName").toString(), params.get("lastName").toString(), params.get("email").toString(), 
				params.get("phoneNumber").toString(), params.get("password").toString(), 
				params.get("birthday"));
	}
	
	public boolean updateUser(Map<String, Object> params) throws Exception{
		um = new UserModel();
		//byte[] pass = Base64.decodeBase64(password.getBytes());
		return um.updateUser(params.get("firstName").toString(), params.get("lastName").toString(), 
				params.get("phoneNumber").toString(), params.get("password").toString(), 
				params.get("birthday"), params.get("email").toString());
	}
}
