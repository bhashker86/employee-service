package com.employeeservice.beans;

import java.util.Objects;


public class Employee {

	private Long id;
	private String employeeName;
	private Double employeeSalary;

    private Employee(Builder builder) {
        this.id = builder.id;
        this.employeeName = builder.employeeName;
        this.employeeSalary = builder.employeeSalary;
    }

    public static class Builder {
        private Long id;
        private String employeeName;
        private Double employeeSalary;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setEmployeeName(String employeeName) {
            this.employeeName = employeeName;
            return this;
        }

        public Builder setEmployeeSalary(Double employeeSalary) {
            this.employeeSalary = employeeSalary;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
	
    }

	public Long getId() {
		return id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public Double getEmployeeSalary() {
		return employeeSalary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", employeeName=" + employeeName + ", employeeSalary=" + employeeSalary + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(employeeName, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(employeeName, other.employeeName) && Objects.equals(id, other.id);
	}

	
}
