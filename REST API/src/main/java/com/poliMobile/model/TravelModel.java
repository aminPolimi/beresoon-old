package com.poliMobile.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.poliMobile.config.DataConfig;

public class TravelModel {

	Connection conn = null;
	PreparedStatement stmt = null;
	
	public TravelModel() throws Exception{
	try{
		Class.forName(DataConfig.JDBC_DRIVER);
		conn = DriverManager.getConnection(DataConfig.DB_URL+DataConfig.DB,DataConfig.USER,DataConfig.PASS);
		}
	catch (Exception e) {
		throw e;
		// TODO: handle exception
		}
	}

	public List<Map<String, Object>> getTravels(int travelID) throws Exception{
		String tID = "";
		if (travelID>0)
			tID = " and t.ID = " + travelID;
		ResultSet res;
			stmt = conn.prepareStatement("select t.ID, t.SourceCityID,t.DestinationCityID,t.UserID,t.TravelDate, "+
											"u.FirstName, u.LastName, u.Email, sc.City SourceCity, dc.City DestinationCity, "+
											"sco.Country SourceCountry, dco.Country DestinationCountry "+
										"from Travels t join city sc on t.SourceCityID=sc.ID "+tID+
										" join City dc on dc.ID=t.DestinationCityID "+
										" join User u on t.UserID=u.ID "+
										" join Country sco on sc.CountryID=sco.ID "+
										" join Country dco on dco.ID=dc.CountryID");
			res = stmt.executeQuery();
			List<Map<String, Object>> ds = resultSetToList(res);
			close();
			return ds;
	}

	public boolean addTravel(Object travelDate, int userID, int srcCityID, int dstCityID) throws Exception{
		stmt = conn.prepareStatement("insert into Travels(TravelDate, UserID, SourceCityID, DestinationCityID) "+
				  						"values (?,?,?,?)");
		stmt.setTimestamp(1, Timestamp.valueOf(travelDate.toString()));
		stmt.setInt(2, userID);
		stmt.setInt(3, srcCityID);
		stmt.setInt(4, dstCityID);
		
		int res = stmt.executeUpdate();
		close();
		if (res>0)
			return true;
		else 
			return false;
	}
	
	public boolean removeProduct(int id) throws Exception{
		stmt = conn.prepareStatement("delete from Travels where ID = ?");
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
