package com.poliMobile.model;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.poliMobile.config.DataConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductModel {
	
	Connection conn = null;
	PreparedStatement stmt = null;
	
	public ProductModel() throws Exception{
		try{
			Class.forName(DataConfig.JDBC_DRIVER);
			conn = DriverManager.getConnection(DataConfig.DB_URL+DataConfig.DB,DataConfig.USER,DataConfig.PASS);
			}
		catch (Exception e) {
			throw e;
			// TODO: handle exception
			}
		}
	
	public List<Map<String, Object>> getProducts(int productID) throws Exception{
		String pID = "";
		if (productID>0)
			pID = " and p.ID = " + productID;
		ResultSet res;
			stmt = conn.prepareStatement("select p.ID, p.`Name`, p.Description, p.Price, p.URL, p.ImageQuantity, "+
											"p.CategoryID, c.`Name` as Category, d.Discount "+
										"from Product p join Category c on p.CategoryID=c.ID and p.Valid = 1 "+ pID +
										" left join Discount d on p.ID=d.ProductID and NOW() BETWEEN d.StartDate and d.EndDate");
			res = stmt.executeQuery();
			List<Map<String, Object>> ds = resultSetToList(res);
			close();
			return ds;
	}
	
	public boolean addProduct(String name, String description, String url, int imageQuantity, float price, int category) throws Exception{
		stmt = conn.prepareStatement("insert into Product(name, description, URL, ImageQuantity, price, CategoryID) "+
					"values(?,?,?,?,?,?)");
		stmt.setString(1, name);
		stmt.setString(2, description);
		stmt.setString(3, url);
		stmt.setInt(4, imageQuantity);
		stmt.setFloat(5, price);
		stmt.setInt(6, category);
		int res = stmt.executeUpdate();
		close();
		if (res>0)
			return true;
		else 
			return false;
	}
	
	public boolean removeProduct(int id) throws Exception{
		stmt = conn.prepareStatement("delete from Product where ID = ?");
		stmt.setInt(1, id);
		int res = stmt.executeUpdate();
		close();
		if(res>0)
			return true;
		else 
			return false;
	}
	
	public  List<Map<String, Object>> getCategories() throws Exception{
		ResultSet res;
		stmt = conn.prepareStatement("select * from Category");
		res = stmt.executeQuery();
		List<Map<String, Object>> ds = resultSetToList(res);
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
