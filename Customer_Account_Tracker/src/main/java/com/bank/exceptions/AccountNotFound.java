package com.bank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFound extends Exception {
	
	String msg;
	public AccountNotFound(String msg) {
		super(msg);
	}
}
