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

public class OffersModel {
	
	Connection conn = null;
	PreparedStatement stmt = null;
	
	public OffersModel() throws Exception{
		try{
			Class.forName(DataConfig.JDBC_DRIVER);
			conn = DriverManager.getConnection(DataConfig.DB_URL+DataConfig.DB,DataConfig.USER,DataConfig.PASS);
			}
		catch (Exception e) {
			throw e;
			// TODO: handle exception
			}
	}
	
	public List<Map<String, Object>> getOffers(int requestsID) throws Exception{
		ResultSet res;
			stmt = conn.prepareStatement("select o.ID, o.RegisterDate, o.Reward, c.City, co.Country, u.FirstName, u.LastName "+
											"from Offers o join Requests r on o.RequestsID = r.ID and o.RequestsID = ? "+
												"join `User` u on o.UserID = u.ID and "+
												"join City c on o.CityID = c.City "+
												"join Country co on c.CountryID = co.ID");
			stmt.setInt(1, requestsID);
			res = stmt.executeQuery();
			List<Map<String, Object>> ds = resultSetToList(res);
			close();
			return ds;
	}
	
	public boolean addOffer(int requestID, int userID, int cityID, float reward) throws Exception{
		stmt = conn.prepareStatement("insert into Offers (RequestId, UserID, CityID, Reward) "+
					"values(?,?,?,?)");
		stmt.setInt(1, requestID);
		stmt.setInt(2, userID);
		stmt.setInt(3, cityID);
		stmt.setFloat(4, reward);
		int res = stmt.executeUpdate();
		close();
		if (res>0)
			return true;
		else 
			return false;
	}
	
	public boolean removeOffer(int id) throws Exception{
		stmt = conn.prepareStatement("delete from Offers where ID = ?");
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
