package com.employeeservice.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.employeeservice.beans.Employee;
import com.employeeservice.util.EmployeeUtil;

@Component
public class EmployeeDAO {
	
	
	private  Map<String,Double> empSal=new HashMap<>();
	public EmployeeDAO() {
		empSal=setUpData();
	}
	public Map<String,Double> setUpData() {
		List<Employee> empList=EmployeeUtil.createEmployees();
		return EmployeeUtil.createEmpSalDb(empList);
	}
	
	public Optional<Double> findSalaryByName(String userName) {
		
		return Optional.ofNullable(empSal.get(userName.toLowerCase()));
	}
	public  Map<String,Double> getAllEmpDbDump(){
		return empSal;
	}

}
