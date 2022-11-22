package com.booking.BookingApp.service.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.FetchNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.BookingApp.entity.Product;
import com.booking.BookingApp.repository.ProductRepository;
import com.booking.BookingApp.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public Product saveProduct(Product product) {
		  return productRepository.save(product);
	
	}

	@Override
	public List<Product> fetchProductList() {
		
		return productRepository.findAll();
	}

	@Override
	public Product updateProduct(Product product, Long productId) {
		Optional<Product> fetched =  productRepository.findById(productId);
		
		if(fetched.get()!= null) {
			
			Product fromDb = fetched.get();
			// update the attributes
			if(product.getName()!= null) {
				
				fromDb.setName(product.getName());
			}
			
            if(product.getPrice()!= null) {
				
				fromDb.setPrice(product.getPrice());
			}
            
            if(product.getIs_active()!= fromDb.getIs_active()) {
				
				fromDb.setIs_active(product.getIs_active());
			}
            return productRepository.save(fromDb);
		} else {
			throw new FetchNotFoundException("Product with ID " + productId + " Not found", product);
		}
	}

	@Override
	public void deleteProductById(Long productId) {
		
		if(productRepository.existsById(productId)) {
			  productRepository.deleteById(productId);   
		}
	}

}
