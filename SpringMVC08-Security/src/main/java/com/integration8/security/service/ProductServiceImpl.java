package com.integration8.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integration8.security.model.Product;
import com.integration8.security.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> findProducts() {
		return productRepository.findAll();
	}

	@Override
	public void addProduct(Product producto) {
		productRepository.save(producto);
	}

	@Override
	public void deleteProduct(Product producto) {
		productRepository.delete(producto);
	}

	@Override
	public Product getProductById(long id) {
		Product producto = productRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid producto Id:" + id));
		return producto;
	}

}
