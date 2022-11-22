package com.booking.BookingApp.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.booking.BookingApp.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
     @Modifying
     @Query(value = "Update order_products set status = :status where id = :id", nativeQuery = true)
	 void updateStatus(String status, Long id);
     
     @Modifying
     @Query(value = "Update order_products set status = :status where id = :id", nativeQuery = true)
	 void cancelOrder(String status, Long id);
     
     //@Modifying
    // @Query(value = "select * from order_products where status IN ('DELIVERED','CANCELLED','REIMBURSED')", nativeQuery = true)
	 List<Order> findByStatusIn(List<String> status);
	 
	 @Modifying
     @Query(value = "select * from order_products where created_date >= :start and created_date <= :end", nativeQuery = true)
	 List<Order> getOrdersBetweenDate(LocalDateTime start, LocalDateTime end);
     
	 
}
