package com.poliMobile.bussines;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poliMobile.model.DiscountModel;

@Component
public class DiscountBL {

	DiscountModel dis;
	
	public String getDiscount(int discountID) throws Exception{
		dis=new DiscountModel();
		List<Map<String, Object>> msg = dis.getDiscounts(discountID);
		return new ObjectMapper().writeValueAsString(msg);
	}
	
	public boolean addDiscount(Map<String, Object> params) throws Exception{
		dis=new DiscountModel();
		return dis.addDiscount((Integer)params.get("productID"), (Float)params.get("discount"), 
				(Date)params.get("startDate"), (Date)params.get("endDate"));
	}
	
	public boolean removeDiscount(int id) throws Exception{
		dis=new DiscountModel();
		return dis.removeDiscount(id);
	}
	
}
