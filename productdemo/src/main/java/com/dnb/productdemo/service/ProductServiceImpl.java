package com.dnb.productdemo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dnb.productdemo.dto.Product;
import com.dnb.productdemo.exceptions.IdNotFoundException;
import com.dnb.productdemo.exceptions.UniqueProductNameExpection;
import com.dnb.productdemo.repo.ProductRepository;
@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository productRepository;
	public Product createProduct(Product product) {
		// We are creating the product using save method by crud operation that is provided by prodrepo
		return productRepository.save(product);
	}

	public Optional<Product> getProductById(String productId) {
		// we get the product details with the help of findbyid(implict method of crudrepo) 
		return productRepository.findById(productId);
	}

	public boolean deleteProductByID(String productId) throws IdNotFoundException {
		// Here we are checking whether the id present/not and throwing an exception if id id not dound
		if(productRepository.existsById(productId)) {
		 productRepository.deleteById(productId);
		}
		else
			throw new IdNotFoundException("Given Id doesnt exits in the list");
		return true;
	
	}

	public Iterable<Product> getAllProducts() {
		// findall method to get all the products
		return productRepository.findAll();
	}

	public boolean checkproductId(String productId) throws IdNotFoundException {
		// checking wther the product exists oor not.
		if(productRepository.existsById(productId)) {
			return productRepository.existsById(productId);}
		else
			throw new IdNotFoundException("Given Id doesnt exits in the list");
		
	}

	public Product UniqueUpdateName(String productId, Product updatedProduct) throws UniqueProductNameExpection, IdNotFoundException{
		// Here we check whether we have product of the mentioned id or not and update the product name
		Product existingProduct = productRepository.findById(productId).orElseThrow(() -> new IdNotFoundException("Product not found with given ID"));
		if(!existingProduct.getProductName().equals(updatedProduct.getProductName())) {
			if(productRepository.findByProductName(updatedProduct.getProductName()).isPresent()) {
				throw new UniqueProductNameExpection("Product Name must be unique");
			}
		}
		updatedProduct.setProductId(productId);
		return productRepository.save(updatedProduct);

	}

}
