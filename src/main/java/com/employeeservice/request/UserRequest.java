package com.employeeservice.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserRequest {

	@NotBlank(message = "Name is required and cannot be empty.")
	@Pattern(regexp = "^[A-Za-z][A-Za-z ]*$", message = "Name must start with a letter and contain only letters and spaces.")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
