package com.booking.BookingApp.model;

import java.util.List;

public class OrderModel {
	
	private Double total_amount;
	
	private String status;
	
	private List<OrderItemModel> orderItems;

	public Double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(Double total_amount) {
		this.total_amount = total_amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<OrderItemModel> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemModel> orderItems) {
		this.orderItems = orderItems;
	}
	
}
