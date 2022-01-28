package com.iiht.training.employee.testutils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.iiht.training.employee.dto.CertificatesDto;
import com.iiht.training.employee.dto.EmployeeDto;
import com.iiht.training.employee.dto.SkillsDto;

public class MasterData {

	public static EmployeeDto getEmployeeDto() {
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setId(1);
		employeeDto.setUsername("user");
		employeeDto.setPassword("user@123");
		employeeDto.setAddress("New Delhi");
		employeeDto.setPhoneNumber(9812309123L);
		employeeDto.setExperience(12);
		employeeDto.setSalary(58000.00);

		return employeeDto;
	}

	public static List<EmployeeDto> getEmployeeDtoList() {
		List<EmployeeDto> employeeDtos = new ArrayList<>();
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setId(1);
		employeeDto.setUsername("user");
		employeeDto.setPassword("user@123");
		employeeDto.setAddress("New Delhi");
		employeeDto.setPhoneNumber(9812309123L);
		employeeDto.setExperience(12);
		employeeDto.setSalary(58000.00);
		employeeDtos.add(employeeDto);
		employeeDto = new EmployeeDto();
		employeeDto.setId(2);
		employeeDto.setUsername("admin");
		employeeDto.setPassword("admin@123");
		employeeDto.setAddress("Noida");
		employeeDto.setPhoneNumber(9002309123L);
		employeeDto.setExperience(10);
		employeeDto.setSalary(50000.00);
		employeeDtos.add(employeeDto);
		return employeeDtos;

	}

	public static CertificatesDto getCertificatesDto() {
		CertificatesDto dto = new CertificatesDto();
		dto.setId(1);
		dto.setEmployeeId(1);
		dto.setDescription("Oracle Certified Java Associate");
		dto.setSkillName("Java");
		dto.setDateOfIssue(LocalDate.now());
		dto.setDateOfExpiry(LocalDate.of(2025, 10, 20));
		dto.setPassingScore(86);
		dto.setMaxScore(100);
		dto.setScorePercentage(86);
		return dto;
	}

	public static List<CertificatesDto> getCertificatesDtoList() {
		List<CertificatesDto> dtos = new ArrayList<>();
		CertificatesDto dto = new CertificatesDto();
		dto.setId(1);
		dto.setEmployeeId(1);
		dto.setDescription("Oracle Certified Java Associate");
		dto.setSkillName("Java");
		dto.setDateOfIssue(LocalDate.now());
		dto.setDateOfExpiry(LocalDate.of(2025, 10, 20));
		dto.setPassingScore(86);
		dto.setMaxScore(100);
		dto.setScorePercentage(86);
		dtos.add(dto);
		dto = new CertificatesDto();
		dto.setId(2);
		dto.setEmployeeId(2);
		dto.setDescription("Oracle Certified Java Professional");
		dto.setSkillName("Java");
		dto.setDateOfIssue(LocalDate.now());
		dto.setDateOfExpiry(LocalDate.of(2023, 10, 20));
		dto.setPassingScore(78);
		dto.setMaxScore(100);
		dto.setScorePercentage(78);
		dtos.add(dto);
		return dtos;

	}

	public static SkillsDto getSkillsDto() {
		SkillsDto dto = new SkillsDto();
		dto.setId(1);
		dto.setEmployeeId(1);
		dto.setName("Java 11");
		dto.setDescription("Java 11 with additional Features");
		dto.setProfeciencyLevel("Intermediate");
		dto.setYearsOfExperience("11 Years");
		dto.setEnvironment("8080");
		return dto;

	}

	public static List<SkillsDto> getSkillsDtoList() {
		List<SkillsDto> dtos = new ArrayList<>();
		SkillsDto dto = new SkillsDto();
		dto.setId(1);
		dto.setEmployeeId(1);
		dto.setName("Java 11");
		dto.setDescription("Java 11 with additional Features");
		dto.setProfeciencyLevel("Intermediate");
		dto.setYearsOfExperience("11 Years");
		dto.setEnvironment("8080");
		dtos.add(dto);
		dto = new SkillsDto();
		dto.setId(2);
		dto.setEmployeeId(2);
		dto.setName("Python 3.0");
		dto.setDescription("Python with Data Science");
		dto.setProfeciencyLevel("Intermediate");
		dto.setYearsOfExperience("8 Years");
		dto.setEnvironment("9092");
		dtos.add(dto);
		return dtos;
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			final String jsonContent = mapper.writeValueAsString(obj);

			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
