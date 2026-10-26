package com.project.products.controller;
import com.project.products.entity.Product;
import com.project.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/products")
public class Productcontroller {
    @Autowired
        private ProductRepository productRepository;

    @PostMapping("/batch")
    public List<Product> addproduct(@RequestBody List<Product> product)
    {

        return  productRepository.saveAll(product);
    }

    @GetMapping("/batch")
    public List<Product> showproduct()
    {

        return productRepository.findAll();
    }

    //sorted
    @GetMapping("/sorted")
    public List<Product> getSortedproducts(@RequestParam String sortBy) //requestparam  is read the value after ? sortby price ,categoty..
    {
        return productRepository.findAll(Sort.by(sortBy));
    }

    @PutMapping("/batch/{id}")
    public Product updateProducts(@RequestBody Product product,@PathVariable Integer id)
    {
        product.setProductId(id);
        return productRepository.save(product);
    }

    @DeleteMapping("/batch/{id}")
    public String  deleteProducts(@PathVariable Integer id)
    {
        productRepository.deleteById(id);
        return "Product deleted Succesfully";
    }
}
