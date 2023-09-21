package com.dnb.productdemo.Utils;

import org.springframework.stereotype.Component;

import com.dnb.productdemo.dto.Product;
import com.dnb.productdemo.payload.request.ProductRequest;
/**

* The RequestToEntityMapper class is responsible for mapping ProductRequest objects to Product entities. 
* It provides methods for converting incoming request data into entity objects that can be saved to the database.

*/
@Component
public class RequestToEntityMapper {
	
	public Product getProductentityobject(ProductRequest productRequest) {
		Product product= new Product();
		product.setExpiryDate(productRequest.getProductExpiry());
		product.setProductCatergory(productRequest.getProductCategory());
		product.setProductDescription(productRequest.getProductDescription());
		product.setProductName(productRequest.getProductName());
		product.setProductPrice(productRequest.getProductPrice());
		return product;
	}

}

