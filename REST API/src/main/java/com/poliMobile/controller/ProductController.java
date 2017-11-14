package com.poliMobile.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poliMobile.bussines.ProductBL;

@RestController
public class ProductController {
	
	@Autowired
	ProductBL pm = new ProductBL();
	
	@PostMapping("/product/add")
	public ResponseEntity addProduct(@RequestBody Map<String,Object> prod){
		try{
			return new ResponseEntity(pm.addProduct(prod),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/products")
	public ResponseEntity getProducts(){
		try{
			return new ResponseEntity(pm.getProducts(0),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity getProduct(@PathVariable("id") int id ){
		try{
			return new ResponseEntity(pm.getProducts(id),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/product/remove/{id}")
	public ResponseEntity removeProduct(@PathVariable("id") int id){
		try{
			return new ResponseEntity(pm.removeProduct(id),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/category")
	public ResponseEntity getCategory(){
		try{
			return new ResponseEntity(pm.getCategories(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
		}
	}

}
