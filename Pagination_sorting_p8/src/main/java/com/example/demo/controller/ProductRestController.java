package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepostiroy;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    @Autowired
    private ProductRepostiroy productRepostiroy;
    
    // getProducts() is our own controller method.
    // It accepts page number, page size, and field name for sorting.
    @GetMapping("/page")
    public Page<Product> getProducts(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy) {

    	// PageRequest creates a pagination and sorting request.
        // page = page number (starts from 0)
        // size = number of products per page
        // sortBy = Product field used for sorting
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortBy)
        );
        
     // findAll(pageable) is provided by JpaRepository.
        // It returns the requested page of products with sorting.
        // Page<Product> contains products and pagination information.
        return productRepostiroy.findAll(pageable);
    }
    
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product)
    {
    	Product savepr=productRepostiroy.save(product);
    	return ResponseEntity.status(HttpStatus.CREATED).body(savepr);
    }
    
    @PutMapping("/{id}")
    public Product updateProduct(
            @RequestBody Product product,
            @PathVariable Integer id) {

        Product existingProduct = productRepostiroy.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        existingProduct.setPrice(product.getPrice());

        return productRepostiroy.save(existingProduct);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id)
    {
    	 productRepostiroy.deleteById(id);
    	 return ResponseEntity.noContent().build();
    }
}