package com.dnb.jdbcexam.controller;

import java.util.Optional;

import javax.naming.InvalidNameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnb.jdbcexam.dto.Product;
import com.dnb.jdbcexam.exception.IdNotFoundException;
import com.dnb.jdbcexam.exception.InvalidDateException;
import com.dnb.jdbcexam.exception.InvalidIdException;
import com.dnb.jdbcexam.service.ProductService;
import com.dnb.jdbcexam.repo.ProductRepository;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	ProductService productService;
	@Autowired
	ProductRepository productRepository;

    // Delete a product by its ID
	@DeleteMapping("/{productId}")
	public ResponseEntity<?> deleteproductById(@PathVariable("productId") String productId) throws IdNotFoundException {
		try {
			productService.deleteProduct(productId);
			return ResponseEntity.ok("Deleted product Successfully");
		} catch (IdNotFoundException e) {
			// TODO Auto-generated catch block
			throw new IdNotFoundException("Product Id Not Found");
		}
	}
	
    // Get a product by its ID
	@GetMapping("/{productId}")
	public ResponseEntity<?> getProductById
	(@PathVariable("productId") String productId) throws IdNotFoundException, InvalidNameException, InvalidDateException, InvalidIdException {
		Optional<Product> optional = productService.getProductById(productId);
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}
		else {
			throw new IdNotFoundException("Id not valid");
		}
	}
	
    // Create a new product
	@PostMapping("/create")
	public ResponseEntity<?> createProduct(@Valid @RequestBody Product product) throws InvalidNameException, com.dnb.jdbcexam.exception.InvalidNameException{
		//By using getByname method we see if the name is unique or not if the name is fetched from previous entity objects then it throws the duplicate name exception
		Optional<Product> optional = productService.getProductByName(product.getProductName());
		if(optional.isEmpty()) return ResponseEntity.ok(productService.createProduct(product));
		else
			throw new InvalidNameException("Same Product already exists");
	}

    // Get all products
	@GetMapping("/getall")
	public ResponseEntity<?> getAllProduct() throws InvalidNameException, InvalidDateException, InvalidIdException  {
		Iterable<Product> listofProd = productService.getAllProduct();
		return ResponseEntity.ok(listofProd);

	}
	
    // Update a product by its ID
	@PostMapping("/update/{productId}")

	public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable("productId") String productId) throws InvalidIdException, IdNotFoundException, com.dnb.jdbcexam.exception.InvalidNameException {
		Optional<Product> OptionalProduct;

		if(productService.getProductById(productId) != null) {
			product.setProductId(productId);
			OptionalProduct = productService.updateProduct(product);
		}
		else
			throw new IdNotFoundException("Product Id Doesn't exist");
		return new ResponseEntity(OptionalProduct, HttpStatus.OK);
	}
}
