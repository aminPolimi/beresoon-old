package com.poliMobile.bussines;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poliMobile.model.ChatModel;

@Component
public class ChatBL {

	ChatModel chm;
	
	public String getChats(int requestID,int userID, int partnerID) throws Exception{
		chm=new ChatModel();
		List<Map<String, Object>> msg = chm.getChats(requestID, userID, partnerID);
		String mapAsJson = new ObjectMapper().writeValueAsString(msg);
		return mapAsJson;
	}
	
	public boolean addChat(Map<String, Object> chat) throws Exception{
		chm=new ChatModel();
		return chm.addChat(chat.get("message").toString(), (Integer)chat.get("partnerUserID"), (Integer)chat.get("requestID"));
	}
	
	public boolean removeChat(int id) throws Exception{
		chm=new ChatModel();
		return chm.removeChat(id);
	}
	
}
