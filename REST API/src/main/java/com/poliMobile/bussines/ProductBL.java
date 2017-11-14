package com.poliMobile.bussines;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poliMobile.model.ProductModel;

@Component
public class ProductBL {
	
	ProductModel pm;
	
	public String getProducts(int productID) throws Exception{
		pm = new ProductModel();
		List<Map<String, Object>> msg = pm.getProducts(productID);
		String mapAsJson = new ObjectMapper().writeValueAsString(msg);
		return mapAsJson;
	}
	
	public boolean addProduct(Map<String, Object> prod) throws Exception{
		pm = new ProductModel();
		return pm.addProduct(prod.get("name").toString(), prod.get("description").toString(), prod.get("url").toString(),
				(Integer)prod.get("imageQuantity"), Float.valueOf(prod.get("price").toString()), (Integer)prod.get("category"));
	}
	
	public boolean removeProduct(int id) throws Exception{
		pm = new ProductModel();
		return pm.removeProduct(id);
	}
	
	public String getCategories() throws Exception{
		pm=new ProductModel();
		List<Map<String, Object>> msg = pm.getCategories();
		return new ObjectMapper().writeValueAsString(msg);
	}
	

}
