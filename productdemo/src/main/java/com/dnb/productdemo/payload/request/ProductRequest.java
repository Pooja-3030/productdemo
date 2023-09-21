package com.dnb.productdemo.payload.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ProductRequest {
	@NotBlank(message="product name must be mentioned")
	private String productName;
	@NotBlank(message = "Product description must be filled")
	private String productDescription;
	private LocalDate productExpiry;
	@Min(message = "price should not be negative", value = 0)
	private long productPrice;
	private String productCategory;

}