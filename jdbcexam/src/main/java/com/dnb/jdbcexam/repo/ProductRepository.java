package com.dnb.jdbcexam.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dnb.jdbcexam.dto.Product;
//Repository interface for product data access
@Repository
public interface ProductRepository extends CrudRepository<Product, String>{
	public Optional<Product> findByProductName(String productName);
}
