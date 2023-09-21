package com.dnb.productdemo.service;


import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dnb.productdemo.dto.Product;
import com.dnb.productdemo.exceptions.IdNotFoundException;
import com.dnb.productdemo.exceptions.UniqueProductNameExpection;
// The ProductService interface defines methods that need to be implemented by the serviceimpl class to manage products

@Service
public interface ProductService {
	public Product createProduct(Product product);
	public Optional<Product> getProductById(String productId);
	public boolean deleteProductByID(String productId) throws IdNotFoundException;
	public Iterable<Product> getAllProducts();	
	public boolean checkproductId(String productId) throws IdNotFoundException;
	public Product UniqueUpdateName(String productName,Product updatedProduct) throws UniqueProductNameExpection,IdNotFoundException;
	
}
