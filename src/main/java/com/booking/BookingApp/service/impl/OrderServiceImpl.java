package com.booking.BookingApp.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.BookingApp.entity.Order;
import com.booking.BookingApp.entity.OrderItem;
import com.booking.BookingApp.entity.Product;
import com.booking.BookingApp.model.OrderItemModel;
import com.booking.BookingApp.model.OrderModel;
import com.booking.BookingApp.repository.OrderItemRepository;
import com.booking.BookingApp.repository.OrderRepository;
import com.booking.BookingApp.repository.ProductRepository;
import com.booking.BookingApp.service.OrderService;
import com.booking.BookingApp.util.OrderUtil;
import com.booking.BookingApp.util.Status;

@Service
public class OrderServiceImpl implements OrderService {

	private static final String CANCELLED = "CANCELLED";
	private static final String DATE_FORMATTER = "yyyy-MM-dd HH:mm:ss";
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	@Transactional
	public void createOrder(OrderModel order) {

		List<OrderItemModel> products = order.getOrderItems();
		Double totalOrderAmount = 0.0;

		List<Long> productIds = new ArrayList<>();

		for (OrderItemModel o : products) {

			productIds.add(o.getProduct_id());
		}

		List<Product> retrievedProducts = productRepository.findAllById(productIds);

		OrderItem orderItem;

		for (OrderItemModel o : products) {

			List<Product> prods = retrievedProducts.stream().filter(p -> p.getId() == o.getProduct_id())
					.collect(Collectors.toList());
			if (!prods.isEmpty())
				totalOrderAmount += o.getQuantity() * prods.get(0).getPrice();

		}

		Order orderEntity = new Order();
		orderEntity.setTotal_amount(totalOrderAmount);
		orderEntity.setStatus(Status.NEW.getValue());
		orderRepository.save(orderEntity);

		for (OrderItemModel o : products) {

			orderItem = new OrderItem();

			List<Product> prods = retrievedProducts.stream().filter(p -> p.getId() == o.getProduct_id())
					.collect(Collectors.toList());
			if (!prods.isEmpty()) {
				orderItem.setProduct(prods.get(0));
				orderItem.setQuantity(o.getQuantity());
				orderItem.setOrder(orderEntity);
				orderItemRepository.save(orderItem);
			}
		}
	}

	@Override
	@Transactional
	public String updateOrder(Long orderId, String status) throws Exception {
		Optional<Order> order = orderRepository.findById(orderId);

		Map<String, List<String>> statusMap = OrderUtil.getOrderStatusMappings();
		if (order.isPresent()) {
			Order orderEntity = order.get();
			List<String> statuses = statusMap.get(orderEntity.getStatus());
			if (statuses.contains(status.toUpperCase())) {
				orderRepository.updateStatus(status.toUpperCase(), orderId);
				return "Order Status Updated";
			} else {
				throw new Exception(" The Status of the Order ID " + orderId + " can't be updated to " + status);
			}
		} else {

			throw new Exception(" ORDER with ID " + orderId + " NOT FOUND ");
		}
	}

	@Override
	@Transactional
	public String cancelOrder(Long orderId) throws Exception {
		Optional<Order> order = orderRepository.findById(orderId);

		if (order.isPresent()) {
			if (order.get().getStatus().equalsIgnoreCase("NEW")) {
				orderRepository.cancelOrder(CANCELLED, orderId);
				return "Order with ID " + orderId + " is Cancelled";
			} else {
				throw new Exception(" The Order " + orderId + " can't be cancelled ");
			}
		} else {
			throw new Exception("ORDER with ID " + orderId + " NOT FOUND  ");
		}

	}

	@Override
	public String getStatus(Long orderId) throws Exception {
		Optional<Order> order = orderRepository.findById(orderId);

		if (order.isPresent()) {
			return order.get().getStatus();
		} else {
			throw new Exception("ORDER with ID " + orderId + " NOT FOUND  ");
		}
	}

	@Override
	public List<Order> getCompletedOrders() throws Exception {

		try {
			List<String> status = new ArrayList<>();
			status.add(CANCELLED);
			status.add("REIMBURSED");
			status.add("DELIVERED");

			return orderRepository.findByStatusIn(status);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<Order> getOrders(LocalDateTime start, LocalDateTime end) {
		return orderRepository.getOrdersBetweenDate(start, end);
	}

}
