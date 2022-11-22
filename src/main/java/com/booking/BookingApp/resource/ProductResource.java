package com.booking.BookingApp.resource;


import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.booking.BookingApp.entity.Product;


public interface ProductResource {
	
	/**
     * Create {@link Booking} resource
     *
     * @param booking the booking object
     * @return the created booking
     */
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    Product create(@RequestBody Product product);
    
    
    @RequestMapping(value = "/products/{productId}", method = RequestMethod.PUT)
    Product update(@RequestBody Product product, @PathVariable("productId") Long productId);
    
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable("id") Long productId);
    
    /**
     * Create {@link products} resource
     *
     * @param products the products object
     * @return the created products
     */
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    List<Product> getProducts();
	

}
