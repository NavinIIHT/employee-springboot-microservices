package com.iiht.training.employee.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.iiht.training.employee.dto.CertificatesDto;
import com.iiht.training.employee.dto.EmployeeDto;
import com.iiht.training.employee.dto.SkillsDto;
import com.iiht.training.employee.exceptions.InvalidDataException;
import com.iiht.training.employee.model.Employee;
import com.iiht.training.employee.proxy.CertificateServiceProxy;
import com.iiht.training.employee.proxy.SkillsServiceProxy;
import com.iiht.training.employee.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private SkillsServiceProxy proxy;

	@Autowired
	private CertificateServiceProxy serviceProxy;

	@GetMapping("/employees/{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Integer id) {
		EmployeeDto employeeDto = employeeService.getById(id);
		return ResponseEntity.ok(employeeDto);

	}

	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
		List<EmployeeDto> list = employeeService.findAll();
		return ResponseEntity.ok(list);
	}

	@PostMapping("/employees")
	public ResponseEntity<EmployeeDto> addEmployee(@Valid @RequestBody EmployeeDto employeeDto, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException("The Employee Data is not Valid");
		}
		EmployeeDto employee = employeeService.saveEmployee(employeeDto);
		return ResponseEntity.ok(employee);
	}

	@PutMapping("/employees")
	public ResponseEntity<EmployeeDto> updateEmployee(@Valid @RequestBody EmployeeDto employeeDto,
			BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException("The Employee Data is not Valid");
		}
		EmployeeDto employee = employeeService.updateEmployee(employeeDto);
		return ResponseEntity.ok(employee);
	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Boolean> updateEmployee(@PathVariable("id") Integer id) {
		employeeService.deleteEmployee(id);
		return ResponseEntity.ok(true);
	}

	@PostMapping("/employees/login")
	public String employeeLogin(@RequestBody Employee employee) {
		boolean validEmployee = employeeService.isValidEmployee(employee.getUsername(), employee.getPassword());
		if (validEmployee) {
			return "LoggedIn Successfully";
		} else
			return "Username or Password does not match";

	}

	@GetMapping("/employees/skills-by-{id}")
	public List<SkillsDto> getSkillsByEmployeeId(@PathVariable("id") Integer id) {

		HashMap<String, Integer> uriVariables = new HashMap<>();
		uriVariables.put("id", id);
		SkillsDto[] list = new RestTemplate().getForObject("http://localhost:8090/api/skills/by/{id}",
				SkillsDto[].class, uriVariables);

		return Arrays.asList(list);
	}

//	@GetMapping("/employees/skills-by-feign-{id}")
	@GetMapping("/employees/skills/{id}")
	public List<SkillsDto> getSkillsByEmployeeIdFeign(@PathVariable("id") Integer id) {

		List<SkillsDto> list = proxy.skillsByEmployeeId(id);
		return list;
	}

	@GetMapping("/employees/certificates-by-{id}")
	public List<CertificatesDto> getCertificatesByEmployeeId(@PathVariable("id") Integer id) {

		HashMap<String, Integer> uriVariables = new HashMap<>();
		uriVariables.put("id", id);
		CertificatesDto[] list = new RestTemplate().getForObject("http://localhost:9000/api/certificates/employee/{id}",
				CertificatesDto[].class, uriVariables);

		return Arrays.asList(list);
	}

	@GetMapping("/employees/certificates/{id}")
	public List<CertificatesDto> getCertificatesByEmployeeIdFeign(@PathVariable("id") Integer id) {

		List<CertificatesDto> list = serviceProxy.getCertificatesByEmployeeId(id);
		return list;
	}

}
