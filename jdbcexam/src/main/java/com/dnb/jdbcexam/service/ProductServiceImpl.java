package com.dnb.jdbcexam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dnb.jdbcexam.dto.Product;
import com.dnb.jdbcexam.exception.IdNotFoundException;
import com.dnb.jdbcexam.exception.InvalidDateException;
import com.dnb.jdbcexam.exception.InvalidIdException;
import com.dnb.jdbcexam.exception.InvalidNameException;
import com.dnb.jdbcexam.repo.ProductRepository;
import java.util.List;
import java.util.Optional;

//Service implementation for product-related operations
@Service
public class ProductServiceImpl implements ProductService {

	    @Autowired
	    public ProductRepository productRepository;

	    @Override
	    public Product createProduct(Product product)  {
	    	return productRepository.save(product);
	    }

		@Override
		public Optional<Product> getProductById(String ProductId) throws InvalidIdException, IdNotFoundException {
			// TODO Auto-generated method stub
			return productRepository.findById(ProductId);
		}

		@Override
		public boolean deleteProduct(String ProductId) throws IdNotFoundException {
			if (productRepository.existsById(ProductId)) {
				productRepository.deleteById(ProductId);
				
				if (!productRepository.existsById(ProductId)) {
					return true;
				}
				return false;
			}else {
				throw new IdNotFoundException("Product Id Not Found");
			}
		}

		@Override
			public Optional<Product> updateProduct(Product product) throws InvalidNameException{
				Optional<Product> product2 = productRepository.findByProductName(product.getProductName());
				if(product2.isEmpty()){
						productRepository.save(product);
							return Optional.of(product);
				}else {
								throw new InvalidNameException("Prouct name should unique.");
							}
			}

		@Override
		public List<Product> getAllProduct()
				throws javax.naming.InvalidNameException, InvalidDateException, InvalidIdException {
			// TODO Auto-generated method stub
			return (List<Product>) productRepository.findAll();
		}

		@Override
		public Optional<Product> getProductByName(String productName) throws InvalidNameException {
			// TODO Auto-generated method stub
			if(productRepository.findByProductName(productName) != null) return productRepository.findByProductName(productName);

			else throw new InvalidNameException("No product with the given name Exists");
		}
	}
