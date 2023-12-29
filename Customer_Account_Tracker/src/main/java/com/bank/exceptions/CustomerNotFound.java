package com.bank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFound extends Exception {
	
	String msg;
	public CustomerNotFound(String msg) {
		super(msg);
	}
}
