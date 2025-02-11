package com.employeeservice.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.employeeservice.response.ApiResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmployeeControllerIT {

	@Autowired
	private TestRestTemplate restTemplate;

	private final String BASE_URL = "/api/v1/employees";

	@Test
	@DisplayName("Should return all employee salaries")
	void testGetAllEmpSalaries() {
		ResponseEntity<Map> response = restTemplate.getForEntity(BASE_URL + "/salaries", Map.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody()).containsKey("ram");
	}

	@Test
	@DisplayName("Should return salary for a valid employee")
	void testGetEmpSalary_ValidEmployee() {
		String employeeName = "sonu";

		ResponseEntity<ApiResponse> response = restTemplate.getForEntity(BASE_URL + "/" + employeeName + "/salary",
				ApiResponse.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getStatus()).isEqualTo("Success");
		assertThat(response.getBody().getData()).isEqualTo(50000.0);
	}

	@Test
	@DisplayName("404 for an employee not found")
	void testGetEmpSalary_EmployeeNotFound() {
		String employeeName = "wiproFakeHR";

		ResponseEntity<ApiResponse> response = restTemplate.getForEntity(BASE_URL + "/" + employeeName + "/salary",
				ApiResponse.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getMessage()).contains("Data not found");
	}

	@Test
	@DisplayName("204 when salary is zero")
	void testGetEmpSalary_SalaryZero() {
		String employeeName = "raman";

		ResponseEntity<ApiResponse> response = restTemplate.getForEntity(BASE_URL + "/" + employeeName + "/salary",
				ApiResponse.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

	}

	@Test
	@DisplayName("400 for invalid employee name")
	void testGetEmpSalary_InvalidEmployeeName() {
		String invalidEmployeeName = "wiproFakeHR_";

		ResponseEntity<ApiResponse> response = restTemplate
				.getForEntity(BASE_URL + "/" + invalidEmployeeName + "/salary", ApiResponse.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getMessage()).contains("Invalid Input");
	}

}
