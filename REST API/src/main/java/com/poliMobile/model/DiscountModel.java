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

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.poliMobile.config.DataConfig;

public class DiscountModel {

	Connection conn = null;
	PreparedStatement stmt = null;
	
	public DiscountModel() throws Exception{
		try{
			Class.forName(DataConfig.JDBC_DRIVER);
			conn = DriverManager.getConnection(DataConfig.DB_URL+DataConfig.DB,DataConfig.USER,DataConfig.PASS);
			}
		catch (Exception e) {
			throw e;
			// TODO: handle exception
			}
	}
	
	public List<Map<String, Object>> getDiscounts(int discountID) throws Exception{
		String dID = "";
		if (discountID>0)
			dID = " and d.ID = " + discountID;
		ResultSet res;
			stmt = conn.prepareStatement("select p.ID, p.`Name`, p.Description, p.Price, p.URL, p.ImageQuantity, "+
											"p.CategoryID, c.`Name` as Category, d.Discount "+
										 "from Discount d join Product p on p.ID=d.ProductID "+ dID +
										     " and NOW() BETWEEN d.StartDate and d.EndDate and p.Valid = 1 "+ 
										 " join Category c on p.CategoryID=c.ID");
			res = stmt.executeQuery();
			List<Map<String, Object>> ds = resultSetToList(res);
			close();
			return ds;
	}
	
	public boolean addDiscount(int productID, float discount, Date startDate, Date endDate) throws Exception{
		stmt = conn.prepareStatement("insert into Discount(ProductID, Discount, StartDate, EndDate) values (?,?,?,?) ");
		stmt.setInt(1, productID);
		stmt.setFloat(2, discount);
		stmt.setDate(3, startDate);
		stmt.setDate(4, endDate);
		int res = stmt.executeUpdate();
		close();
		if (res>0)
			return true;
		else 
			return false;
	}
	
	public boolean removeDiscount(int id) throws Exception{
		stmt = conn.prepareStatement("delete from Discount where ID = ?");
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
