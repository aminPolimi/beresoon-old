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

public class CityModel {

	Connection conn = null;
	PreparedStatement stmt = null;
	
	public CityModel() throws Exception{
		try{
			Class.forName(DataConfig.JDBC_DRIVER);
			conn = DriverManager.getConnection(DataConfig.DB_URL+DataConfig.DB,DataConfig.USER,DataConfig.PASS);
			}
		catch (Exception e) {
			throw e;
			// TODO: handle exception
			}
		}
	
	public List<Map<String, Object>> getCities(int countryID) throws Exception{
		ResultSet result;
		stmt = conn.prepareStatement("select co.Country, ci.City, ci.CountryID, ci.ID "+
											"from Country co join City ci on co.ID = ci.CountryID "+
									"where ci.CountryID = ?");
		stmt.setInt(1, countryID);
		result = stmt.executeQuery();
		List<Map<String, Object>> ds = resultSetToList(result);
		close();
		return ds;
	}
	
	public List<Map<String, Object>> getCountries() throws Exception{
		ResultSet result;
		stmt = conn.prepareStatement("select * from Country ");
		result = stmt.executeQuery();
		List<Map<String, Object>> ds = resultSetToList(result);
		close();
		return ds;
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
