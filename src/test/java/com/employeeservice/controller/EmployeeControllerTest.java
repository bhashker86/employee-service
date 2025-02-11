package com.employeeservice.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.employeeservice.exception.ResourceNotFoundException;
import com.employeeservice.exception.SalaryNotFoundException;
import com.employeeservice.service.EmployeeService;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	/*
	 * @Mock private EmployeeService employeeService;
	 * 
	 * @InjectMocks private EmployeeController employeeController;
	 */

	@MockBean
	private EmployeeService employeeService;

	@Test
	// @Disabled
	@DisplayName("Test getAllEmpDb - Retrieve All Employee Salaries")
	void testGetAllEmpDb() throws Exception {
		// Mock Data
		Map<String, Double> mockData = new HashMap<>();
		mockData.put("sonu", 50000.0);
		mockData.put("monu", 60000.0);

		when(employeeService.getAllEmpSalary()).thenReturn(mockData);

		mockMvc.perform(get("/api/v1/employees/salaries").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.sonu").value(50000.0))
				.andExpect(jsonPath("$.monu").value(60000.0));

		verify(employeeService, times(1)).getAllEmpSalary();
	}

	@Test
	@DisplayName("Test getEmpSalary - Valid Employee Name")
	// @Disabled
	void testGetEmpSalary_ValidName() throws Exception {
		String employeeName = "Sonu";
		Double salary = 55000.0;

		when(employeeService.findSalaryByName(employeeName)).thenReturn(salary);

		mockMvc.perform(
				get("/api/v1/employees/{employeeName}/salary", employeeName).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.status").value("Success"))
				.andExpect(jsonPath("$.message").value("Salary Found")).andExpect(jsonPath("$.data").value(salary));

		verify(employeeService, times(1)).findSalaryByName(employeeName);
	}

	@Test
	@DisplayName("Test getEmpSalary - Invalid Input")
	// @Disabled
	void testGetEmpSalary_InvalidName() throws Exception {
		String invalidEmployeeName = "temp_"; // Invalid Input!

		mockMvc.perform(get("/api/v1/employees/{employeeName}/salary", invalidEmployeeName)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

		verify(employeeService, never()).findSalaryByName(anyString());
	}

	@Test
	@DisplayName("Test getEmpSalary - Employee Not Found")
	void testGetEmpSalary_EmployeeNotFound() throws Exception {
		String employeeName = "invalidEmployeeNameFromWipro";

		when(employeeService.findSalaryByName(anyString()))
				.thenThrow(new ResourceNotFoundException("Data not found with provided user name: " + employeeName));

		mockMvc.perform(
				get("/api/v1/employees/{employeeName}/salary", employeeName).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(jsonPath("$.status").value("Error!"))
				.andExpect(jsonPath("$.message").value("Data not found with provided user name: " + employeeName));

		verify(employeeService, times(1)).findSalaryByName(anyString());
	}

	@Test
	@DisplayName("Test getEmpSalary - Salary Not Found")
	void testGetEmpSalary_SalaryNotFound() throws Exception {
		String employeeName = "rahul";

		when(employeeService.findSalaryByName(anyString()))
				.thenThrow(new SalaryNotFoundException("Salary data is missing for employee: " + employeeName));

		mockMvc.perform(
				get("/api/v1/employees/{employeeName}/salary", employeeName).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent()).andExpect(jsonPath("$.status").value("No content !"))
				.andExpect(jsonPath("$.message").value("Salary data is missing for employee: " + employeeName));

		verify(employeeService, times(1)).findSalaryByName(anyString());
	}

}
