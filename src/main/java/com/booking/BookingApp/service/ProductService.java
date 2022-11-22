package com.booking.BookingApp.service;

import java.util.List;

import com.booking.BookingApp.entity.Product;

public interface ProductService {
	  // Save operation
    Product saveProduct(Product department);
  
    // Read operation
    List<Product> fetchProductList();
  
    // Update operation
    Product updateProduct(Product department,
                                Long departmentId);
  
    // Delete operation
    void deleteProductById(Long departmentId);

}
