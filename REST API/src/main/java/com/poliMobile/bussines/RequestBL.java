package com.poliMobile.bussines;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poliMobile.model.ProductModel;
import com.poliMobile.model.RequestModel;

@Component
public class RequestBL {

	RequestModel req;
	
	public String getRequests(int reqID, int userID) throws Exception{
		req = new RequestModel();
		List<Map<String, Object>> msg = req.getRequests(reqID, userID);
		String mapAsJson = new ObjectMapper().writeValueAsString(msg);
		return mapAsJson;
	}
	
	public boolean addRequest(Map<String, Object> params) throws Exception{
		req = new RequestModel();
		return req.addRequest(Integer.valueOf(params.get("srcCityID").toString()), Integer.valueOf(params.get("dstCityID").toString())
				, Integer.valueOf(params.get("userID").toString()), Integer.valueOf(params.get("productID").toString()), 
				params.get("purchaseDate"), Float.valueOf(params.get("reward").toString()), 
				Integer.valueOf(params.get("quantity").toString()), params.get("deliveryDate"));
	}
	
	public boolean removeRequest(int id) throws Exception{
		req = new RequestModel();
		return req.removeRequest(id);
	}
}
