package com.iiht.training.employee.boundary;

import static com.iiht.training.employee.testutils.TestUtils.boundaryTestFile;
import static com.iiht.training.employee.testutils.TestUtils.currentTest;
import static com.iiht.training.employee.testutils.TestUtils.testReport;
import static com.iiht.training.employee.testutils.TestUtils.yakshaAssert;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iiht.training.employee.dto.CertificatesDto;
import com.iiht.training.employee.dto.EmployeeDto;
import com.iiht.training.employee.dto.SkillsDto;
import com.iiht.training.employee.testutils.MasterData;

@ExtendWith(SpringExtension.class)
public class BoundaryTest {
	private static Validator validator;

	// ----------------------------------------------------------------------------------------------
	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void employee_testEmployeeUsernameNotNull() throws Exception {
		EmployeeDto employeeDto = MasterData.getEmployeeDto();
		employeeDto.setUsername(null);
		Set<ConstraintViolation<EmployeeDto>> violations = validator.validate(employeeDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testEmployeeUsernameMinThree() throws Exception {
		EmployeeDto employeeDto = MasterData.getEmployeeDto();
		employeeDto.setUsername("Ab");
		Set<ConstraintViolation<EmployeeDto>> violations = validator.validate(employeeDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testEmployeeUsernameMaxFifty() throws Exception {
		EmployeeDto employeeDto = MasterData.getEmployeeDto();
		String username = "";
		for (int i = 0; i < 60; i++) {
			username.concat("A");
		}
		employeeDto.setUsername(username);
		Set<ConstraintViolation<EmployeeDto>> violations = validator.validate(employeeDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testEmployeePasswordNotNull() throws Exception {
		EmployeeDto employeeDto = MasterData.getEmployeeDto();
		employeeDto.setPassword(null);
		Set<ConstraintViolation<EmployeeDto>> violations = validator.validate(employeeDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testEmployeePasswordMinThree() throws Exception {
		EmployeeDto employeeDto = MasterData.getEmployeeDto();
		employeeDto.setPassword("Ab");
		Set<ConstraintViolation<EmployeeDto>> violations = validator.validate(employeeDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testEmployeePasswordMaxThirty() throws Exception {
		EmployeeDto employeeDto = MasterData.getEmployeeDto();
		String password = "";
		for (int i = 0; i < 40; i++) {
			password.concat("A");
		}
		employeeDto.setPassword(password);
		Set<ConstraintViolation<EmployeeDto>> violations = validator.validate(employeeDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testEmployeeAddressNotNull() throws Exception {
		EmployeeDto employeeDto = MasterData.getEmployeeDto();
		employeeDto.setAddress(null);
		Set<ConstraintViolation<EmployeeDto>> violations = validator.validate(employeeDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testEmployeeAddressMinThree() throws Exception {
		EmployeeDto employeeDto = MasterData.getEmployeeDto();
		employeeDto.setAddress("Ab");
		Set<ConstraintViolation<EmployeeDto>> violations = validator.validate(employeeDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testEmployeeAddressMaxTwoHunders() throws Exception {
		EmployeeDto employeeDto = MasterData.getEmployeeDto();
		String address = "";
		for (int i = 0; i < 220; i++) {
			address.concat("A");
		}
		employeeDto.setAddress(address);
		Set<ConstraintViolation<EmployeeDto>> violations = validator.validate(employeeDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testEmployeePhoneNumberNotNull() throws Exception {
		EmployeeDto employeeDto = MasterData.getEmployeeDto();
		employeeDto.setPhoneNumber(null);
		Set<ConstraintViolation<EmployeeDto>> violations = validator.validate(employeeDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testEmployeePhoneNumberMinTenDigts() throws Exception {
		EmployeeDto employeeDto = MasterData.getEmployeeDto();
		employeeDto.setPhoneNumber(12345678L);
		Set<ConstraintViolation<EmployeeDto>> violations = validator.validate(employeeDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testEmployeePhoneNumberMaxTenDigits() throws Exception {
		EmployeeDto employeeDto = MasterData.getEmployeeDto();
		employeeDto.setPhoneNumber(123456789012L);
		Set<ConstraintViolation<EmployeeDto>> violations = validator.validate(employeeDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testEmployeeSalaryNotNull() throws Exception {
		EmployeeDto employeeDto = MasterData.getEmployeeDto();
		employeeDto.setSalary(null);
		Set<ConstraintViolation<EmployeeDto>> violations = validator.validate(employeeDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testEmployeeExperienceNotNull() throws Exception {
		EmployeeDto employeeDto = MasterData.getEmployeeDto();
		employeeDto.setExperience(null);
		Set<ConstraintViolation<EmployeeDto>> violations = validator.validate(employeeDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testCertificateEmployeeIdNotNull() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setEmployeeId(null);
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testCertificateSkillNameNotNull() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setSkillName(null);
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testCertificateSkillNameMinThree() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setSkillName("Ab");
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testCertificateSkillNameMaxFifty() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		String skillName = "";
		for (int i = 0; i < 220; i++) {
			skillName.concat("A");
		}
		certificatesDto.setSkillName(skillName);
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testCertificateDescriptionNotNull() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setDescription(null);
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testCertificateDescriptionMinThree() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setDescription("Ab");
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testCertificateDescriptionMaxHundered() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		String description = "";
		for (int i = 0; i < 220; i++) {
			description.concat("A");
		}
		certificatesDto.setDescription(description);
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testCertificateDateOfIssueNotNull() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setDateOfIssue(null);
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testCertificateDateOfIssueIsPassDate() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setDateOfIssue(LocalDate.of(2023, 10, 10));
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testCertificateDateOfExpiryNotNull() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setDateOfExpiry(null);
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testCertificateDateOfExpiryIsFutureDate() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setDateOfExpiry(LocalDate.of(2021, 10, 10));
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testCertificatePassingScoreNotNull() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setPassingScore(null);
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testCertificateMaxScoreNotNull() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setMaxScore(null);
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testCertificateScorePercentageNotNull() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setScorePercentage(null);
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testSkillsEmployeeIdNotNull() throws Exception {
		SkillsDto skillsDto = MasterData.getSkillsDto();
		skillsDto.setEmployeeId(null);
		Set<ConstraintViolation<SkillsDto>> violations = validator.validate(skillsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testSkillNameNotNull() throws Exception {
		SkillsDto skillsDto = MasterData.getSkillsDto();
		skillsDto.setName(null);
		Set<ConstraintViolation<SkillsDto>> violations = validator.validate(skillsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testSkillNameMinThree() throws Exception {
		SkillsDto skillsDto = MasterData.getSkillsDto();
		skillsDto.setName("Ab");
		Set<ConstraintViolation<SkillsDto>> violations = validator.validate(skillsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testSkillNameMaxHundered() throws Exception {
		SkillsDto skillsDto = MasterData.getSkillsDto();
		String name = "";
		for (int i = 0; i < 120; i++) {
			name.concat("A");
		}
		skillsDto.setName(name);
		Set<ConstraintViolation<SkillsDto>> violations = validator.validate(skillsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testSkillDescriptionNotNull() throws Exception {
		SkillsDto skillsDto = MasterData.getSkillsDto();
		skillsDto.setDescription(null);
		Set<ConstraintViolation<SkillsDto>> violations = validator.validate(skillsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testSkillDescriptionMinThree() throws Exception {
		SkillsDto skillsDto = MasterData.getSkillsDto();
		skillsDto.setDescription("Ab");
		Set<ConstraintViolation<SkillsDto>> violations = validator.validate(skillsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testSkillDescriptionMaxHundered() throws Exception {
		SkillsDto skillsDto = MasterData.getSkillsDto();
		String description = "";
		for (int i = 0; i < 120; i++) {
			description.concat("A");
		}
		skillsDto.setDescription(description);
		Set<ConstraintViolation<SkillsDto>> violations = validator.validate(skillsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testSkillProfeciencyLevelNotNull() throws Exception {
		SkillsDto skillsDto = MasterData.getSkillsDto();
		skillsDto.setProfeciencyLevel(null);
		Set<ConstraintViolation<SkillsDto>> violations = validator.validate(skillsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testSkillProfeciencyLevelMinThree() throws Exception {
		SkillsDto skillsDto = MasterData.getSkillsDto();
		skillsDto.setProfeciencyLevel("Ab");
		Set<ConstraintViolation<SkillsDto>> violations = validator.validate(skillsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testSkillProfeciencyLevelMaxHundered() throws Exception {
		SkillsDto skillsDto = MasterData.getSkillsDto();
		String profeciencyLevel = "";
		for (int i = 0; i < 120; i++) {
			profeciencyLevel.concat("A");
		}
		skillsDto.setProfeciencyLevel(profeciencyLevel);
		Set<ConstraintViolation<SkillsDto>> violations = validator.validate(skillsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testSkillYearsOfExperienceNotNull() throws Exception {
		SkillsDto skillsDto = MasterData.getSkillsDto();
		skillsDto.setYearsOfExperience(null);
		Set<ConstraintViolation<SkillsDto>> violations = validator.validate(skillsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testSkillYearsOfExperienceMinThree() throws Exception {
		SkillsDto skillsDto = MasterData.getSkillsDto();
		skillsDto.setYearsOfExperience("Ab");
		Set<ConstraintViolation<SkillsDto>> violations = validator.validate(skillsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void employee_testSkillYearsOfExperienceMaxHundered() throws Exception {
		SkillsDto skillsDto = MasterData.getSkillsDto();
		String yearsOfExperience = "";
		for (int i = 0; i < 120; i++) {
			yearsOfExperience.concat("A");
		}
		skillsDto.setYearsOfExperience(yearsOfExperience);
		Set<ConstraintViolation<SkillsDto>> violations = validator.validate(skillsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
}
