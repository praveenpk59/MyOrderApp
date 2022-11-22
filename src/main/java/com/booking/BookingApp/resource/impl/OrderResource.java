package com.booking.BookingApp.resource.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking.BookingApp.entity.Order;
import com.booking.BookingApp.model.OrderModel;
import com.booking.BookingApp.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderResource {

	@Autowired
	private OrderService orderService;

	@PostMapping
	public void createOrder(@RequestBody OrderModel order) {

		orderService.createOrder(order);

	}

	@PutMapping("/{orderId}")
	public String updateOrder(@PathVariable("orderId") long id, @RequestParam("status") String status)
			throws Exception {

		try {
			return orderService.updateOrder(id, status);
		} catch (Exception e) {
			return e.getMessage();
		}

	}

	@PutMapping("cancel/{orderId}")
	public String cancelOrder(@PathVariable("orderId") long id) throws Exception {
		try {
			return orderService.cancelOrder(id);
		} catch (Exception e) {
			return e.getMessage();
		}

	}

	@GetMapping("status/{orderId}")
	public String orderStatus(@PathVariable("orderId") long id) throws Exception {
		try {
			return orderService.getStatus(id);
		} catch (Exception e) {
			return e.getMessage();
		}

	}

	@GetMapping("/completed")
	public List<Order> completedOrders() throws Exception {
		return orderService.getCompletedOrders();
	}

	@GetMapping
	public List<Order> getAlOrdersForATimePeriod(
			@RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
			@RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end)
			throws Exception {
		return orderService.getOrders(start, end);
	}

}
