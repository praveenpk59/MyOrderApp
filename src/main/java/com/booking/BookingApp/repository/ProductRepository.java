package com.booking.BookingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.booking.BookingApp.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Modifying
	@Query(value = "Update Product set is_active = 0 where id = :id", nativeQuery = true )
	void deleteById(@Param("id")Long id);
}
