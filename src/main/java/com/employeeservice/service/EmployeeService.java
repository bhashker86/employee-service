package com.employeeservice.service;

import java.util.Map;

public interface EmployeeService {
	public Double findSalaryByName(String name);
    public Map<String, Double> getAllEmpSalary();
}
