package com.poliMobile.model;

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

import com.poliMobile.config.DataConfig;
import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class AuthenticationModel {

	Connection conn = null;
	PreparedStatement stmt = null;
	
	public AuthenticationModel() throws Exception{
		try{
			Class.forName(DataConfig.JDBC_DRIVER);
			conn = DriverManager.getConnection(DataConfig.DB_URL+DataConfig.DB ,DataConfig.USER,DataConfig.PASS);
			}
		catch (Exception e) {
			throw e;
			// TODO: handle exception
			}
	}
	
	public boolean getAuth(String username, String token) throws Exception{
		ResultSet result;
		stmt = conn.prepareStatement("SELECT count(*) FROM Authentication WHERE email = ? and Token=?");
		stmt.setString(1,username);
		stmt.setString(2,token);
		result = stmt.executeQuery();
		List<Map<String, Object>> ds = resultSetToList(result);
		close();
		if (ds.size()>0)
			return true;
		return false;
	}
	
	public void insertToken(int userId, String token) throws Exception{
		stmt = conn.prepareStatement("insert into Authentication(UserID, Token) values (?, ?)");
		stmt.setInt(1, userId);
		stmt.setString(2, token);
		int result = stmt.executeUpdate();
		close();
	}
	
	public boolean deleteToken(String userName, String token) throws Exception{
		stmt = conn.prepareStatement("delete from Authentication "+
					"where UserID in (select UserId from User where Email = ?)  and Token = ?");
		stmt.setString(1, userName);
		stmt.setString(2, token);
		int result = stmt.executeUpdate();
		close();
		return true;
	}
	
	public boolean removeAuth(String username, String token) throws Exception{
		
		stmt = conn.prepareStatement("delete from Authentication where email = ? and token=?");
		stmt.setString(1, username);
		stmt.setString(2, token);
		int res = stmt.executeUpdate();
		close();
		if(res>0)
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
