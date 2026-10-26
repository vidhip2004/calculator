package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Product;

public interface ProductService {
	
	Product create(Product product);
	List<Product>  getAll();
	Product getById(Integer id);
	Product update(Product product,Integer id);
	Product delete(Integer id);
	
}
