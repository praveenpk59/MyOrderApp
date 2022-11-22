package com.booking.BookingApp.util;

public enum Status {
	
	NEW("NEW"),
	PAID("PAID"),
	SENT("SENT"),
	DEIVERED("DELIVERED"),
	CANCELLED("CANCELLED"),
	REIMBURSED("REIMBURSED");
	
	private String value;

	Status(String string) {
		this.value = string;
	}
	
	public String getValue() {
		return value;
	}
	
}
