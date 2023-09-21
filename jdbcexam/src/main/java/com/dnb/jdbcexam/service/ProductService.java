package com.dnb.jdbcexam.service;

import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import com.dnb.jdbcexam.dto.Product;
import com.dnb.jdbcexam.exception.IdNotFoundException;
import com.dnb.jdbcexam.exception.InvalidDateException;
import com.dnb.jdbcexam.exception.InvalidIdException;

//Service interface for product-related operations
public interface ProductService {
	    public Product createProduct(Product product);
	    public Optional<Product> getProductById(String ProductId) throws InvalidIdException, IdNotFoundException;
	    public boolean deleteProduct(String ProductId) throws IdNotFoundException;
	    public Optional<Product> updateProduct(Product product) throws com.dnb.jdbcexam.exception.InvalidNameException;
	    public List<Product> getAllProduct() throws InvalidNameException, InvalidDateException, InvalidIdException;
		public Optional<Product> getProductByName(String productName) throws com.dnb.jdbcexam.exception.InvalidNameException;

}
