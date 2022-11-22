package com.booking.BookingApp.service;

import java.util.List;

import com.booking.BookingApp.entity.Product;
import com.booking.BookingApp.model.ProductModel;

public interface ProductService {
	  // Save operation
    Product saveProduct(ProductModel model);
  
    // Read operation
    List<Product> fetchProductList();
  
    // Update operation
    Product updateProduct(ProductModel model,
                                Long departmentId);
  
    // Delete operation
    void deleteProductById(Long departmentId);

}
