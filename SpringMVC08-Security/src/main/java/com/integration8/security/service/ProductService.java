package com.integration8.security.service;

import java.util.List;

import com.integration8.security.model.Product;

public interface ProductService {

	List<Product> findProducts();
	void addProduct(Product producto);
	void deleteProduct(Product producto);
	Product getProductById(long id);
	
}
