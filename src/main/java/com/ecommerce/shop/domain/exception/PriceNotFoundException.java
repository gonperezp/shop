package com.ecommerce.shop.domain.exception;

public class PriceNotFoundException extends RuntimeException {

	public PriceNotFoundException(String message) {
		super(message);
	}
}
