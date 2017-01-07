package com.poliMobile.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.poliMobile.model.ProductModel;
import com.poliMobile.model.Product;

@RestController
public class ProductController {
	
	@PostMapping("/product/{userName}")
	public ResponseEntity addItem(@PathVariable("userName") String user, @RequestBody Product prod){
		try{
			ProductModel pm = new ProductModel();
			pm.insertItem(prod, user);
			return new ResponseEntity("inserted",HttpStatus.ACCEPTED);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getProducts")
	public ResponseEntity getProducts(){
		try{
			ProductModel pm = new ProductModel();
			return new ResponseEntity(pm.getProducts(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
		}
	}

}
