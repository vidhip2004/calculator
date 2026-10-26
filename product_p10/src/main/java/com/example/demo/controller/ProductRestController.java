package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<Product> create(@RequestBody Product product)
	{
		Product savedPorduct=productService.create(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedPorduct);
	}
	
	@GetMapping
	public List<Product> getAll()
	{
		return productService.getAll();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> update(@RequestBody Product product,@PathVariable Integer id)
	{
		Product updateproduct=productService.update(product, id);
		if(updateproduct!=null)
		{
			return ResponseEntity.ok(updateproduct);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id)
	{
		Product deleProdouct=productService.delete(id);
		if(deleProdouct!=null)
		{
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
