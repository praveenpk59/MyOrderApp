package com.booking.BookingApp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "order_item")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//@Column(name = "ORDER_ID" )
	//private int order_id;
	
	//@Column(name = "PRODUCT_ID")
	//private Long product_id;
	
	//@Column(name = "QUANTITY")
	private int quantity;
	
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "ORDER_ID")
	 private Order order;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "PRODUCT_ID")
	 private Product product;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    @JsonBackReference
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	 @JsonBackReference
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	
}
