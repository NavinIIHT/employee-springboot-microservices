package com.iiht.training.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.employee.dto.EmployeeDto;
import com.iiht.training.employee.exceptions.EmployeeNotFoundException;
import com.iiht.training.employee.model.Employee;
import com.iiht.training.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeDto, employee);
		employeeRepository.save(employee);
		return employeeDto;
	}

	@Override
	public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeDto, employee);
		employeeRepository.save(employee);
		return employeeDto;
	}

	@Override
	public boolean deleteEmployee(Integer id) {
		EmployeeDto employeeDto = getById(id);
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeDto, employee);
		employeeRepository.delete(employee);
		return true;
	}

	@Override
	public EmployeeDto getById(Integer id) {
		Optional<Employee> findById = employeeRepository.findById(id);
		if (findById.isPresent()) {
			EmployeeDto dto = new EmployeeDto();
			BeanUtils.copyProperties(findById.get(), dto);
			return dto;
		}
		throw new EmployeeNotFoundException("Employee with id " + id + " does not exists");
	}

	@Override
	public List<EmployeeDto> findAll() {
		List<Employee> list = employeeRepository.findAll();
		List<EmployeeDto> employees = new ArrayList<>();
		for (Employee employee : list) {
			EmployeeDto employeeDto = new EmployeeDto();
			BeanUtils.copyProperties(employee, employeeDto);
			employees.add(employeeDto);
		}
		return employees;
	}

	@Override
	public boolean isValidEmployee(String username, String password) {
		Employee emp = employeeRepository.getByUsernameAndPassword(username, password);
		if (emp != null) {
			return true;
		}
		return false;
	}

}
