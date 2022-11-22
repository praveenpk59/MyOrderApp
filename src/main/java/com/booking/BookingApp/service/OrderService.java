package com.booking.BookingApp.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


import com.booking.BookingApp.entity.Order;
import com.booking.BookingApp.model.OrderModel;


public interface OrderService {

	  public void createOrder(OrderModel order);
	  
	  public String cancelOrder(Long orderId) throws Exception ;
	  
	  public String getStatus(Long orderId)  throws Exception;
	  
	  public List<Order> getCompletedOrders()throws Exception;
	  
	  public List<Order> getOrders(LocalDateTime start, LocalDateTime end);

	  public String updateOrder(Long orderId, String status) throws Exception;
	  
}
