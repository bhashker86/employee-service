package com.employeeservice.exception;

public class SalaryNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SalaryNotFoundException(String msg) {
		super(msg);
	}

}
