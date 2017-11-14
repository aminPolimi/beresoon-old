package com.poliMobile.bussines;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.poliMobile.model.TravelModel;

@Component
public class TravelBL {

	TravelModel trv;
	
	public String getTravels(int travelID) throws Exception{
		trv = new TravelModel();
		List<Map<String, Object>> msg = trv.getTravels(travelID);
		String mapAsJson = new ObjectMapper().writeValueAsString(msg);
		return mapAsJson;
	}
	
	public boolean addTravel(Map<String, Object> params) throws Exception{
		trv = new TravelModel();
		return trv.addTravel(params.get("travelDate"), Integer.valueOf(params.get("userID").toString()),
				Integer.valueOf(params.get("srcCityID").toString()), Integer.valueOf(params.get("dstCityID").toString()));
	}
	
	public boolean removeTravel(int id) throws Exception{
		trv = new TravelModel();
		return trv.removeProduct(id);
	}
	
}
