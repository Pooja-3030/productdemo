package com.dnb.productdemo.repo;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.dnb.productdemo.dto.Product;
/**
* The ProductRepository interface is responsible for managing Product entities in the database.
* It extends the CrudRepository, providing basic CRUD operations for products.
*/

@Repository
public interface ProductRepository extends CrudRepository<Product,String>{
	//We have a implicit method for finding product using product name.
    public Optional<Product> findByProductName(String productName);
}
