package com.employeeservice.response;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {

	    private String status;
	    private String message;
	    private T data;
	    private LocalDateTime timestamp;
	    private HttpStatus httpStatus;
	    
	    public ApiResponse() {}
	    
	    private ApiResponse(Builder<T> builder) {
	        this.status = builder.status;
	        this.message = builder.message;
	        this.data = builder.data;
	        this.timestamp = builder.timestamp;
	        this.httpStatus = builder.httpStatus;
	    }
	    public static class Builder<T> {
	        private String status;
	        private String message;
	        private T data;
	        private LocalDateTime timestamp;
	        private HttpStatus httpStatus;

	        public Builder<T> setStatus(String status) {
	            this.status = status;
	            return this;
	        }

	        public Builder<T> setMessage(String message) {
	            this.message = message;
	            return this;
	        }

	        public Builder<T> setData(T data) {
	            this.data = data;
	            return this;
	        }

	        public Builder<T> setTimestamp(LocalDateTime timestamp) {
	            this.timestamp = timestamp;
	            return this;
	        }

	        public Builder<T> setHttpStatus(HttpStatus httpStatus) {
	            this.httpStatus = httpStatus;
	            return this;
	        }

	        public ApiResponse<T> build() {
	            this.timestamp = LocalDateTime.now(); // Set the timestamp to the current time
	            return new ApiResponse<>(this);
	        }

			
	    }

	    // Getters
	    public String getStatus() {
	        return status;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public T getData() {
	        return data;
	    }

	    public LocalDateTime getTimestamp() {
	        return timestamp;
	    }

	    public HttpStatus getHttpStatus() {
	        return httpStatus;
	    }

	    @Override
	    public String toString() {
	        return "ResponseBody{" +
	                "status='" + status + '\'' +
	                ", message='" + message + '\'' +
	                ", data=" + data +
	                ", timestamp=" + timestamp +
	                ", httpStatus=" + httpStatus +
	                '}';
	    }
}
