package com.booking.BookingApp.resource.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.booking.BookingApp.entity.Product;
import com.booking.BookingApp.model.ProductModel;
import com.booking.BookingApp.resource.ProductResource;
import com.booking.BookingApp.service.ProductService;

@RestController
public class ProductResourceImpl implements ProductResource{

	@Autowired
	ProductService productService;
	
	@Override
	public Product create(@Valid @RequestBody ProductModel product) {
		return productService.saveProduct(product);
	}

	@Override
	public Product update(@RequestBody ProductModel product, @PathVariable("productId") Long prodcutId) {
		return productService.updateProduct(product, prodcutId);
	}

	@Override
	public void delete(@PathVariable("id") Long productId) {
		 productService.deleteProductById(productId);
	}

	@Override
	public List<Product> getProducts() {
		return productService.fetchProductList();
	}

}
