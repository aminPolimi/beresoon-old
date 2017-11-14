package com.poliMobile.model;

import java.sql.Connection;
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

public class RequestModel {
	
	Connection conn = null;
	PreparedStatement stmt = null;
	
	public RequestModel() throws Exception{
		try{
			Class.forName(DataConfig.JDBC_DRIVER);
			conn = DriverManager.getConnection(DataConfig.DB_URL+DataConfig.DB,DataConfig.USER,DataConfig.PASS);
			}
		catch (Exception e) {
			throw e;
			// TODO: handle exception
			}
		}
	
	public List<Map<String, Object>> getRequests(int reqID, int userID) throws Exception{
		String rID = "";
		if (reqID > 0)
			rID = " and r.ID = " + reqID;
		ResultSet res;
		stmt = conn.prepareStatement("select r.ID, sc.City as SourceCity, dc.City as DestinationCity, p.`Name` "+ 
										", p.Price, p.Currency, r.PurchaseDate, r.DeliveryDate, r.Quantity, r.Reward "+
										"from Requests r join Product p on r.ProductID=p.ID and r.UserID = ? "+rID+
										" join City dc on r.DestinationCityID=dc.ID "+
										"left join City sc on r.SourceCityID=sc.ID");
		stmt.setInt(1, userID);
		res = stmt.executeQuery();
		List<Map<String, Object>> ds = resultSetToList(res);
		close();
		return ds;
	}
	
	public boolean addRequest(int srcCityID, int dstCityID, int userID, int productID, Object purchaseDate,
			float reward, int quantity, Object deliveryDate) throws Exception{
		stmt = conn.prepareStatement("insert into Requests (SourceCityID, DestinationCityID, UserID, ProductID, "+
										" PurchaseDate, Reward, Quantity, DeliveryDate) values (?,?,?,?,?,?,?,?) ");
		System.out.println(Timestamp.valueOf(purchaseDate.toString()));
		stmt.setInt(1, srcCityID);
		stmt.setInt(2, dstCityID);
		stmt.setInt(3, userID);
		stmt.setInt(4, productID);
		stmt.setTimestamp(5, Timestamp.valueOf(purchaseDate.toString()));
		stmt.setFloat(6, reward);
		stmt.setInt(7, quantity);
		stmt.setTimestamp(8, Timestamp.valueOf(deliveryDate.toString()));
		int res = stmt.executeUpdate();
		close();
		if (res>0)
			return true;
		else 
			return false;
	}
	
	public boolean removeRequest(int id) throws Exception{
		stmt = conn.prepareStatement("delete from Requests where ID=? ");
		stmt.setInt(1, id);
		int res = stmt.executeUpdate();
		close();
		if (res>0)
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
