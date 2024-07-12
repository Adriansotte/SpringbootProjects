package com.integration8.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.integration8.security.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	
}
