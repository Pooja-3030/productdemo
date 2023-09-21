package com.dnb.productdemo.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dnb.productdemo.Utils.RequestToEntityMapper;
import com.dnb.productdemo.dto.Product;
import com.dnb.productdemo.exceptions.IdNotFoundException;
import com.dnb.productdemo.exceptions.UniqueProductNameExpection;
import com.dnb.productdemo.payload.request.ProductRequest;
import com.dnb.productdemo.repo.ProductRepository;
import com.dnb.productdemo.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/products")
public class ProductController {
	@Autowired
	ProductService productService;
	@Autowired
	RequestToEntityMapper mapper;
	@Autowired
	ProductRepository productRepository;
	
	//creates a product using service implemented methods.
	@PostMapping("/create")
	public ResponseEntity<?> createProduct(@Valid @RequestBody ProductRequest product) {
		Product product2= mapper.getProductentityobject(product);
		try {
			Product product3 = productService.createProduct(product2);
			return new ResponseEntity(product3, HttpStatus.CREATED);
		} catch (Exception e) {
			
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	//deletes a mentioned product by its id

	@DeleteMapping("/{productId}")
	public ResponseEntity<?> deleteProductById(@PathVariable("productId") String productId) throws IdNotFoundException {
		
		if (productService.checkproductId(productId)) {
			productService.deleteProductByID(productId);
			return ResponseEntity.noContent().build();
			
		} if (productService.checkproductId(productId)) {
			System.out.println("Deletion not implemented properly, please delete again");
		} else
			throw new IdNotFoundException("Product Id not found");
		return null;
		

	}
	//retrieves the whole prodct using product id

	@GetMapping("/{productId}")
	public ResponseEntity<?> getProductById(@PathVariable("productId") String productId) throws IdNotFoundException {
		Optional<Product> optional = productService.getProductById(productId);
		if (optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		} else {
			throw new IdNotFoundException("Mentioned product id not found");
		}

	}

	// retrieves all the products in the table(database)
	@GetMapping("/all")
	public ResponseEntity<?> getAllProducts() {
		Iterable<Product> optional = productService.getAllProducts();

		return ResponseEntity.ok(optional);
	}
	//Updates the unique productname using productid
	@PutMapping("/{productId}")

	public ResponseEntity<?> updateProduct(@PathVariable String productId, @RequestBody Product updatedProduct) throws IdNotFoundException{

		try {

			productService.UniqueUpdateName(productId, updatedProduct);

			return ResponseEntity.ok("Product updated successfully");

		}catch(UniqueProductNameExpection e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

		}

	}

}
