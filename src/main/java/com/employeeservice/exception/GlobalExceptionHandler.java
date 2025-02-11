package com.employeeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.employeeservice.response.ApiResponse;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> handleResourceNotFoundException(ResourceNotFoundException ex,
			WebRequest request) {
		ApiResponse<String> responseBody = new ApiResponse.Builder<String>().setStatus("Error!")
				.setMessage(ex.getMessage()).setHttpStatus(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SalaryNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> handleSalaryNotFoundException(SalaryNotFoundException ex) {
		ApiResponse<String> responseBody = new ApiResponse.Builder<String>().setStatus("No content !")
				.setMessage(ex.getMessage()).setHttpStatus(HttpStatus.NO_CONTENT).build();
		return new ResponseEntity<>(responseBody, HttpStatus.NO_CONTENT);
	}

	@ExceptionHandler(IllegalArgumentException.class)

	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400 public
	ResponseEntity<ApiResponse<String>> handleInvalidNameException(IllegalArgumentException ex) {
		ApiResponse<String> responseBody = new ApiResponse.Builder<String>().setStatus("Invalid Input!")
				.setMessage(ex.getLocalizedMessage()).setHttpStatus(HttpStatus.BAD_REQUEST).build();

		return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ApiResponse<String>> handleConstraintViolationException(ConstraintViolationException ex) {

		ApiResponse<String> responseBody = new ApiResponse.Builder<String>().setStatus("Invalid Input!")
				.setMessage(ex.getLocalizedMessage()).setHttpStatus(HttpStatus.BAD_REQUEST).build();

		return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<String>> handleGlobalException(Exception ex, WebRequest request) {
		ApiResponse<String> responseBody = new ApiResponse.Builder<String>().setStatus("error")
				.setMessage("An unexpected error occurred").setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR).build();
		return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
