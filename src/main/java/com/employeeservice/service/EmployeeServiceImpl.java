package com.employeeservice.service;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.employeeservice.exception.*;
import com.employeeservice.dao.EmployeeDAO;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Override
	public Double findSalaryByName(String name) {
		
		 return employeeDAO.findSalaryByName(name.toLowerCase())
	                .map(salary -> {
	                    if (salary == null || salary == 0.0) {
	                        throw new SalaryNotFoundException("Salary data is missing for employee:  " + name);
	                    }
	                    
	                    return salary;
	                })
	                .orElseThrow(() -> new ResourceNotFoundException("Data not found  with provide user name: " + name));
		
	}
    
	public Map<String, Double> getAllEmpSalary(){
		 return employeeDAO.getAllEmpDbDump();
	 }
	
}
