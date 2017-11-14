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

public class RateModel {

	Connection conn = null;
	PreparedStatement stmt = null;
	
	public RateModel() throws Exception{
		try{
			Class.forName(DataConfig.JDBC_DRIVER);
			conn = DriverManager.getConnection(DataConfig.DB_URL+DataConfig.DB,DataConfig.USER,DataConfig.PASS);
			}
		catch (Exception e) {
			throw e;
			// TODO: handle exception
			}
	}
	
	public List<Map<String, Object>> getRates(int userID) throws Exception{
		ResultSet res;
			stmt = conn.prepareStatement("select * from Rate");
			res = stmt.executeQuery();
			List<Map<String, Object>> ds = resultSetToList(res);
			close();
			return ds;
	}
	
	public boolean addRate(int paymentID, int rate,String desc) throws Exception{
		stmt = conn.prepareStatement("insert into Rate (PaymentID, rate, Description) values(?,?,?)");
		stmt.setInt(1, paymentID);
		stmt.setInt(2, rate);
		stmt.setString(3, desc);
		int res = stmt.executeUpdate();
		close();
		if (res>0)
			return true;
		else 
			return false;
	}
	
	public boolean removeRate(int id) throws Exception{
		stmt = conn.prepareStatement("delete from Rate where ID = ?");
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
