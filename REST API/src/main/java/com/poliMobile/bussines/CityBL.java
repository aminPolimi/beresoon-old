package com.poliMobile.bussines;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poliMobile.model.CityModel;

@Component
public class CityBL {

	CityModel city ;
	
	public String getCities(int countryID) throws Exception{
		city=new CityModel();
		List<Map<String, Object>> msg = city.getCities(countryID);
		String mapAsJson = new ObjectMapper().writeValueAsString(msg);
		return mapAsJson;
	}
	
	public String getCountries() throws Exception{
		city=new CityModel();
		List<Map<String, Object>> msg = city.getCountries();
		return new ObjectMapper().writeValueAsString(msg);
	}
}
