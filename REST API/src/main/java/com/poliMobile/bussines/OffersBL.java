package com.poliMobile.bussines;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poliMobile.model.OffersModel;

@Component
public class OffersBL {

	OffersModel offm;
	
	public String getOffers(int requestsID) throws Exception{
		offm = new OffersModel();
		List<Map<String, Object>> msg = offm.getOffers(requestsID);
		return new ObjectMapper().writeValueAsString(msg);
	}
	
	public boolean addOffer(Map<String, Object> params) throws Exception{
		offm = new OffersModel();
		return offm.addOffer((Integer)params.get("requestID") , (Integer)params.get("userID")
				, (Integer)params.get("cityID"), (Float)params.get("reward"));
		
	}
	
	public boolean removeOffer(int id) throws Exception{
		offm = new OffersModel();
		return offm.removeOffer(id);
	}
	
}
