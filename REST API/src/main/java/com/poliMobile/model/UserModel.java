package com.poliMobile.model;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.poliMobile.config.DataConfig;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public boolean login(String username, String password, String token) throws Exception{
		ResultSet result;
		stmt = conn.prepareStatement("select ID from User where Email = ? and Password = ? ");
		stmt.setString(1,username);
		stmt.setString(2,password);
		result = stmt.executeQuery();
		List<Map<String, Object>> ds = resultSetToList(result);
		close();
		if (ds.size()>0)
			{
				AuthenticationModel auth=new AuthenticationModel();
				auth.insertToken(Integer.parseInt( ds.get(0).get("ID").toString()), token);
				return true;
			}
		return false;
	}

	
	
	
	public List<Map<String, Object>> getUser(int id) throws Exception{
		ResultSet result;
		stmt = conn.prepareStatement("SELECT FirstName, LastName, Email, PhoneNumber, Birthday FROM User WHERE ID = ?");
		stmt.setInt(1,id);
		result = stmt.executeQuery();
		List<Map<String, Object>> ds = resultSetToList(result);
		close();
		return ds;
	}
	
	
	public boolean updateUser(String firstName, String lastName,String phoneNumber,String password, Object birthday, String email) throws Exception{
		stmt = conn.prepareStatement("update User set FirstName = case when ISNULL(?) = 1 then FirstName else ? END "+
				",LastName = case when ISNULL(?) = 1 then LastName else ? END "+
				",PhoneNumber = case when ISNULL(?) = 1 then PhoneNumber else ? END "+
				",Password = case when ISNULL(?) = 1 then Password else ? END "+
				",Birthday = case when ISNULL(?) = 1 then Birthday else ? END "+
						"where email = ?");
		stmt.setString(1, firstName);
		stmt.setString(2, firstName);
		stmt.setString(3, lastName);
		stmt.setString(4, lastName);
		stmt.setString(5, phoneNumber);
		stmt.setString(6, phoneNumber);
		stmt.setString(7, password);
		stmt.setString(8, password);
		if (birthday.toString()=="")
			stmt.setDate(9, null);
		else stmt.setDate(9, Date.valueOf(birthday.toString()));
		if (birthday.toString()=="")
			stmt.setDate(10, null);
		else stmt.setDate(10, Date.valueOf(birthday.toString()));
		stmt.setString(11, email);
		int result = stmt.executeUpdate();
		close();
		if (result > 0)
			return true;
		else
			return false;
	}
	public boolean addUser(String firstName, String lastName, String email, String phoneNumber, String password, Object birthday) throws Exception{
			stmt = conn.prepareStatement("insert into User(FirstName, LastName, Email, PhoneNumber,Password,"+
					" Birthday) values(?,?,?,?,?,?)");
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.setString(3, email);
			stmt.setString(4, phoneNumber);
			stmt.setString(5, password);
			System.out.println(birthday.toString());
			if (birthday.toString()=="")
				stmt.setDate(6, null);
			else stmt.setDate(6, Date.valueOf(birthday.toString()));
			int result = stmt.executeUpdate();
			close();
			if (result > 0)
				return true;
			else
				return false;
	}
	
	
	private List<Map<String, Object>> resultSetToList(ResultSet rs) throws SQLException {
	    ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
	    int columns = md.getColumnCount();
	    List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
	    while (rs.next()){
	        Map<String, Object> row = new HashMap<String, Object>(columns);
	        for(int i = 1; i <= columns; ++i){
	            row.put(md.getColumnName(i), rs.getObject(i));
	        }
	        rows.add(row);
	    }
	    return rows;
	}
	
	private void close() {
	    try {
	            if (stmt != null) {
	            	stmt.close();
	            }
	
	            if (conn != null) {
	                    conn.close();
	            }
	        } catch (Exception e) {
	
	        }
	}
}
