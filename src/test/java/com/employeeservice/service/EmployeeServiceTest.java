package com.employeeservice.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.employeeservice.dao.EmployeeDAO;
import com.employeeservice.exception.ResourceNotFoundException;
import com.employeeservice.exception.SalaryNotFoundException;
import com.employeeservice.service.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

	@Mock
    private EmployeeDAO employeeDAO;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

	/*
	 * @BeforeEach void setUp() { MockitoAnnotations.openMocks(this); }
	 */
    
    @Test
    @DisplayName("mthod:testFindSalaryByName_ValidEmployee")
    void testFindSalaryByName_ValidEmployee() {
        String employeeName = "ram";
        Double expectedSalary = 60000.0;

        when(employeeDAO.findSalaryByName(employeeName.toLowerCase())).thenReturn(Optional.of(expectedSalary));

        Double actualSalary = employeeService.findSalaryByName(employeeName);
        assertEquals(expectedSalary, actualSalary, "Salary should match expected value");
    }

    @Test
    @DisplayName("Method:testFindSalaryByName_EmployeeNotFound")
    void testFindSalaryByName_EmployeeNotFound() {
        String employeeName = "wipro";

        when(employeeDAO.findSalaryByName(employeeName.toLowerCase())).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.findSalaryByName(employeeName);
        });

        assertEquals("Data not found  with provide user name: wipro", exception.getMessage());
    }

    @Test
    @Disabled
    @DisplayName("Method:testFindSalaryByName_SalaryNull")
    void testFindSalaryByName_SalaryNull() {
        String employeeName = "rahul";

        when(employeeDAO.findSalaryByName(employeeName.toLowerCase())).thenReturn(Optional.ofNullable(null));

        Exception exception = assertThrows(SalaryNotFoundException.class, () -> {
            employeeService.findSalaryByName(employeeName);
        });

        assertEquals("Salary data is missing for employee:  rahul", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw SalaryNotFoundException if salary is 0.0")
    void testFindSalaryByName_SalaryZero() {
        String employeeName = "raman";

        when(employeeDAO.findSalaryByName(employeeName.toLowerCase())).thenReturn(Optional.of(0.0));

        Exception exception = assertThrows(SalaryNotFoundException.class, () -> {
            employeeService.findSalaryByName(employeeName);
        });

        assertEquals("Salary data is missing for employee:  raman", exception.getMessage());
    }

    @Test
   // @Disabled
    @DisplayName("Should return all employee salaries from database")
    void testGetAllEmpSalary() {
        Map<String, Double> mockSalaryDb = Map.of(
                "ram", 60000.0,
                "monu", 55000.0,
                "sonu", 50000.0
        );

        when(employeeDAO.getAllEmpDbDump()).thenReturn(mockSalaryDb);

        Map<String, Double> result = employeeService.getAllEmpSalary();

        assertAll(
                () -> assertEquals(3, result.size(), "Size of salary database should match expected"),
                () -> assertEquals(60000.0, result.get("ram"), "Salary should match for 'ram'"),
                () -> assertEquals(55000.0, result.get("monu"), "Salary should match for 'monu'"),
                () -> assertEquals(50000.0, result.get("sonu"), "Salary should match for 'sonu'")
        );
    }
}
