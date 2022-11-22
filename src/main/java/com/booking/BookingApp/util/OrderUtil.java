package com.booking.BookingApp.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderUtil {
	
	
	private static Map<String, List<String>> map = new HashMap<>();
	
	public static Map<String, List<String>> getOrderStatusMappings() {
		
		List<String> list = new ArrayList<>();
		list.add("PAID");
		list.add("CANCELLED");
		map.put("NEW", list);
		list = new ArrayList<>();
		list.add("SENT");
		list.add("REIMBURSED");
		map.put("PAID", list);
		list = new ArrayList<>();
		list.add("DELIVERED");
		map.put("SENT", list);
		
		return  map;
		
		
	}

}
