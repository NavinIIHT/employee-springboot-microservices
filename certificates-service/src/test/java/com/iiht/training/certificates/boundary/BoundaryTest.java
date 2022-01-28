package com.iiht.training.certificates.boundary;

import static com.iiht.training.certificates.testutils.TestUtils.boundaryTestFile;
import static com.iiht.training.certificates.testutils.TestUtils.currentTest;
import static com.iiht.training.certificates.testutils.TestUtils.testReport;
import static com.iiht.training.certificates.testutils.TestUtils.yakshaAssert;

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

import com.iiht.training.certificates.dto.CertificatesDto;
import com.iiht.training.certificates.testutils.MasterData;

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
	public void certificates_testCertificateEmployeeIdNotNull() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setEmployeeId(null);
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void certificates_testCertificateSkillNameNotNull() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setSkillName(null);
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void certificates_testCertificateSkillNameMinThree() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setSkillName("Ab");
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void certificates_testCertificateSkillNameMaxFifty() throws Exception {
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
	public void certificates_testCertificateDescriptionNotNull() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setDescription(null);
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void certificates_testCertificateDescriptionMinThree() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setDescription("Ab");
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void certificates_testCertificateDescriptionMaxHundered() throws Exception {
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
	public void certificates_testCertificateDateOfIssueNotNull() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setDateOfIssue(null);
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void certificates_testCertificateDateOfIssueIsPassDate() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setDateOfIssue(LocalDate.of(2023, 10, 10));
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void certificates_testCertificateDateOfExpiryNotNull() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setDateOfExpiry(null);
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void certificates_testCertificateDateOfExpiryIsFutureDate() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setDateOfExpiry(LocalDate.of(2021, 10, 10));
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void certificates_testCertificatePassingScoreNotNull() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setPassingScore(null);
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void certificates_testCertificateMaxScoreNotNull() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setMaxScore(null);
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

}
