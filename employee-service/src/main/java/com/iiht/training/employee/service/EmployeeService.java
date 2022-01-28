package com.iiht.training.employee.service;

import java.util.List;

import com.iiht.training.employee.dto.EmployeeDto;

public interface EmployeeService {

	public EmployeeDto saveEmployee(EmployeeDto employeeDto);

	public EmployeeDto updateEmployee(EmployeeDto employeeDto);

	public boolean deleteEmployee(Integer id);

	public EmployeeDto getById(Integer id);

	public List<EmployeeDto> findAll();

	public boolean isValidEmployee(String username, String password);
}
