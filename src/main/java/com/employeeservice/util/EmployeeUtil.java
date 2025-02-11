package com.employeeservice.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.employeeservice.beans.Employee;

@Component
public class EmployeeUtil {

	
	 public static List<Employee> createEmployees() {
	        List<Employee> employeeList = new ArrayList<>();

	        employeeList.add(new Employee.Builder().setId(1L).setEmployeeName("sonu").setEmployeeSalary(50000.0).build());
	        employeeList.add(new Employee.Builder().setId(2L).setEmployeeName("monu").setEmployeeSalary(55000.0).build());
	        employeeList.add(new Employee.Builder().setId(3L).setEmployeeName("ram").setEmployeeSalary(60000.0).build());
	        employeeList.add(new Employee.Builder().setId(4L).setEmployeeName("bhashker kumar").setEmployeeSalary(45000.0).build());
	        employeeList.add(new Employee.Builder().setId(5L).setEmployeeName("raman").setEmployeeSalary(0.0).build());
	        employeeList.add(new Employee.Builder().setId(6L).setEmployeeName("sonali").setEmployeeSalary(48000.0).build());
	        employeeList.add(new Employee.Builder().setId(7L).setEmployeeName("nishant").setEmployeeSalary(52000.0).build());
	        employeeList.add(new Employee.Builder().setId(8L).setEmployeeName("hitler").setEmployeeSalary(9993000.0).build());
	        employeeList.add(new Employee.Builder().setId(9L).setEmployeeName("rahul").setEmployeeSalary(null).build());
	        employeeList.add(new Employee.Builder().setId(10L).setEmployeeName("trumph").setEmployeeSalary(62000.0).build());

	        return employeeList;
	    }
	 
	 public static Map<String,Double> createEmpSalDb(  List<Employee> employeeList){
		 Map<String,Double> empSalMap=new HashMap<>();
		 
		 employeeList.stream().forEach(emp->empSalMap.put(emp.getEmployeeName(), emp.getEmployeeSalary()));
		 
		 return empSalMap;
		}
	 
	
}
