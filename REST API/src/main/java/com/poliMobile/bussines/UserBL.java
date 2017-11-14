package com.poliMobile.bussines;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poliMobile.model.UserModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;
import javax.xml.bind.DatatypeConverter;

@Component
public class UserBL {

	UserModel um ;
	General gen=new General();
	
	public String login(String code)throws Exception {
		um = new UserModel();
		//byte[] pass = Base64.decodeBase64(password.getBytes());
		//return um.login(email,new String(pass));
		
		String jwt = Jwts.builder()
				  .setSubject("users/TzMUocMF4p")
				  //.setExpiration(new Date(1300819380))
				  .claim("email", "amin_fathi65@yahoo.com")
				  .claim("password", "1234")
				  .signWith(
				    SignatureAlgorithm.HS256,
				    "secret".getBytes("UTF-8")
				  )
				  .compact();
		
		Jws<Claims> claims = Jwts.parser()
				  .setSigningKey("secret".getBytes("UTF-8"))
				  .parseClaimsJws(jwt);
		
		
		
		System.out.println(claims.getBody().get("email"));
		String token =  gen.generateToken();
		Boolean res = um.login("","", token);
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
