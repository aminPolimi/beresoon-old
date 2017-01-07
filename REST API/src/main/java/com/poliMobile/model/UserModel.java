package com.poliMobile.model;

import com.poliMobile.config.DataConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserModel {

	Connection conn = null;
	PreparedStatement stmt = null;
	
	public UserModel() throws Exception{
		try{
			Class.forName(DataConfig.JDBC_DRIVER);
			conn = DriverManager.getConnection(DataConfig.DB_URL+DataConfig.DB,DataConfig.USER,DataConfig.PASS);
			}
		catch (Exception e) {
			throw e;
			// TODO: handle exception
			}
		}
	
	
	public String login(String username, String password){
		ResultSet result;
		try{
		stmt = conn.prepareStatement("SELECT * FROM user WHERE userName = ? and password=?");
		stmt.setString(1,username);
		stmt.setString(2,password);
		result = stmt.executeQuery();
		//return result.toString();
		if (result.first())
			return "true";
		}catch(Exception e){
			return e.getMessage();
		}
		return "false";
	}

	public void create(User usr) throws Exception{
		try{
			stmt = conn.prepareStatement("insert into user(userName, password) values(?,?)");
			stmt.setString(1, usr.getUserName());
			stmt.setString(2, usr.getPassword());
			stmt.executeQuery();
		}catch (Exception e) {
			throw e;
			// TODO: handle exception
		}
	}
}
