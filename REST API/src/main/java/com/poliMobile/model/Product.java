package com.poliMobile.model;

public class Product {
	
	String name;
	String description;
	float price;
	int quantity;
	String link;
	String userName;
	
	public Product(String name,String description, float price, int quantity, String link, String userName){
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.link = link;
		this.userName = userName;
	}
	
	public Product(){
		
	}
	
	public String getName(){
		return name;
	}
	public String getDescription(){
		return description;
	}
	public float getPrice(){
		return price;
	}
	public int getQuantity(){
		return quantity;
	}
	public String getLink(){
		return link;
	}
	
	
}
