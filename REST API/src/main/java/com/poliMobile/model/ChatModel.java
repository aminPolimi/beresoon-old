package com.poliMobile.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.poliMobile.config.DataConfig;

public class ChatModel {

	Connection conn = null;
	PreparedStatement stmt = null;
	
	public ChatModel() throws Exception{
		try{
			Class.forName(DataConfig.JDBC_DRIVER);
			conn = DriverManager.getConnection(DataConfig.DB_URL+DataConfig.DB ,DataConfig.USER,DataConfig.PASS);
			}
		catch (Exception e) {
			throw e;
			// TODO: handle exception
			}
	}
	
	public List<Map<String, Object>> getChats(int requestID,int userID, int partnerID) throws Exception{
		String rID = "";
		if (requestID>0)
			rID = " and r.ID = " + requestID;
		if (userID>0)
			rID += " and u.ID = " + userID;
		if (partnerID>0)
			rID += " and up.ID = " + partnerID;
		
		ResultSet res;
			stmt = conn.prepareStatement("select c.ID, c.RegisterDate, c.Message, u.ID, u.FirstName, u.LastName, "+
									"c.PartnerUserID, up.FirstName partnerName, up.LastName partnerLastName "+
								"from Chat c join Requests r on r.ID=c.RequestsID "+ 
								"join `User` up on up.ID=c.PartnerUserID "+
								"join `User` u on u.ID=r.UserID "+rID);
			res = stmt.executeQuery();
			List<Map<String, Object>> ds = resultSetToList(res);
			close();
			return ds;
	}
	
	public boolean addChat(String message,int partnerID, int requestID) throws Exception{
		stmt = conn.prepareStatement("insert into Chat (Message, PartnerUserID, RequestsID) values(?,?,?)");
		stmt.setString(1, message);
		stmt.setInt(2, partnerID);
		stmt.setInt(3, requestID);
		int res = stmt.executeUpdate();
		close();
		if (res>0)
			return true;
		else 
			return false;
	}
	
	public boolean removeChat(int id) throws Exception{
		stmt = conn.prepareStatement("delete from Chat where ID = ? and 1=0");
		stmt.setInt(1, id);
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
