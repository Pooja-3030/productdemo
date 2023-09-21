package com.dnb.productdemo.dto;

import java.time.LocalDate;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.dnb.productdemo.Utils.CustomProductIdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
// The Product entity represents a product.It is used for storing information about products, including name, price, and description etc..,.

@Data
@Entity
public class Product {
	@Id //in generated value we generate our own number to be stored in the databse witha format that is specified in the custprodidgenerator class
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
	@GenericGenerator(name = "product_seq", strategy = "com.dnb.productdemo.Utils.CustomProductIdGenerator", parameters = {
			@Parameter(name = CustomProductIdGenerator.INCREMENT_PARAM, value = "50"),
			@Parameter(name = CustomProductIdGenerator.VALUE_PREFIX_PARAMETER, value = "PROD_"),
			@Parameter(name = CustomProductIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
			})
	private String productId;
	@Column(unique = true)
	@NotBlank(message="product name must be mentioned")
	private String productName;
	@NotNull
	@Min(value = 0, message="value should not be negative")
	private long productPrice;
	private String productCatergory;
	@NotBlank(message="Product Description must be provided")
	private String productDescription;
	//@NotBlank(message="every product must come witha expiry date")
	private LocalDate expiryDate;

}
