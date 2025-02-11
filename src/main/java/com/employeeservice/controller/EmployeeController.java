package com.employeeservice.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import  com.employeeservice.response.ApiResponse;
import com.employeeservice.service.EmployeeService;

import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping("api/v1/employees")
@Validated
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@GetMapping("/salaries")
	public ResponseEntity<Map<String, Double> > getAllEmpDb(){
		Map<String, Double> employeesSalary=employeeService.getAllEmpSalary();
		return new ResponseEntity<>(employeesSalary,HttpStatus.OK);
	}
	
	@GetMapping("/{employeeName}/salary")
	public ResponseEntity<ApiResponse<Double>> getEmpSalary(@PathVariable 
			@Pattern(regexp = "^[A-Za-z0-9\\s-]+$", 
			message = "Invalid Input") 
            String employeeName){
		
		Double salary=employeeService.findSalaryByName(employeeName);
		
		ApiResponse<Double> resBody=new ApiResponse.Builder<Double>()
				                                    .setStatus("Success")
				                                    .setTimestamp( LocalDateTime.now())
				                                    .setData(salary)
				                                    .setHttpStatus(HttpStatus.OK)
				                                    .setMessage("Salary Found").build();
		return new ResponseEntity<>(resBody,HttpStatus.OK);
	}
	

}
