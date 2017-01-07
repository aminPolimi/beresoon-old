package com.poliMobile.model;

import com.poliMobile.config.DataConfig;
import com.poliMobile.model.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	
	public void insertItem(Product prod, String user) throws Exception{
		try{
		stmt = conn.prepareStatement("insert into product(name, description, price, quantity, link, userName) "+
					"values(?,?,?,?,?,?)");
		stmt.setString(1, prod.getName());
		stmt.setString(2, prod.getDescription());
		stmt.setFloat(3, prod.getPrice());
		stmt.setInt(4, prod.getQuantity());
		stmt.setString(5, prod.getLink());
		stmt.setString(6, user);
		stmt.executeQuery();
		}catch (Exception e) {
			throw e;
			// TODO: handle exception
		}
	}
	
	public List<Product> getProducts() throws Exception{
		ResultSet res;
		try{
			stmt = conn.prepareStatement("SELECT * FROM product ");
			res = stmt.executeQuery();
			List<Product> nameList = new ArrayList<Product>();
			while(res.next()) {
			    nameList.add(new Product(res.getString("name"),res.getString("description"),
			    	Float.parseFloat(res.getString("price")),Integer.parseInt(res.getString("quantity")),res.getString("link"),res.getString("userName")));
			}
			return nameList;
		}catch(Exception e){
			throw e;
		}
	}
}
