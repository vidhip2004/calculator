package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepostiroy;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepostiroy productRepostiroy;
	
	@Override
	public Product create(Product product)
	{
		return productRepostiroy.save(product);
	}

	@Override
	public List<Product> getAll() {
		return productRepostiroy.findAll();
	}

	@Override
	public Product getById(Integer id) {
		// TODO Auto-generated method stub
		return productRepostiroy.findById(id).orElse(null);
	}

	@Override
	public Product update(Product product, Integer id) {
		// TODO Auto-generated method stub
		Product existingProduct=productRepostiroy.findById(id).orElse(null);
		
		if(existingProduct!=null)
		{
			existingProduct.setProductName(product.getProductName());
			existingProduct.setCategory(product.getCategory());
			existingProduct.setPrice(product.getPrice());
			existingProduct.setQuantity(product.getQuantity());
			
			return productRepostiroy.save(existingProduct);
		}
		return null;
	}

	@Override
	public Product delete(Integer id) {
		// TODO Auto-generated method stub
		Product exisProduct=productRepostiroy.findById(id).orElse(null);
		
		if(exisProduct!=null)
		{
			productRepostiroy.deleteById(id);
		}
		return exisProduct;
	}

}
