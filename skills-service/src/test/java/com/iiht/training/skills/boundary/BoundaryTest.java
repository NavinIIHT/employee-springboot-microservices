package com.iiht.training.skills.boundary;

import static com.iiht.training.skills.testutils.TestUtils.boundaryTestFile;
import static com.iiht.training.skills.testutils.TestUtils.currentTest;
import static com.iiht.training.skills.testutils.TestUtils.testReport;
import static com.iiht.training.skills.testutils.TestUtils.yakshaAssert;

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

import com.iiht.training.skills.dto.CertificatesDto;
import com.iiht.training.skills.dto.SkillsDto;
import com.iiht.training.skills.testutils.MasterData;

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
	public void skills_testCertificateEmployeeIdNotNull() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setEmployeeId(null);
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void skills_testCertificateSkillNameNotNull() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setSkillName(null);
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void skills_testCertificateSkillNameMinThree() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setSkillName("Ab");
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void skills_testCertificateSkillNameMaxFifty() throws Exception {
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
	public void skills_testCertificateDescriptionNotNull() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setDescription(null);
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void skills_testCertificateDescriptionMinThree() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setDescription("Ab");
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void skills_testCertificateDescriptionMaxHundered() throws Exception {
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
	public void skills_testCertificateDateOfIssueNotNull() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setDateOfIssue(null);
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void skills_testCertificateDateOfIssueIsPassDate() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setDateOfIssue(LocalDate.of(2023, 10, 10));
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void skills_testCertificateDateOfExpiryNotNull() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setDateOfExpiry(null);
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void skills_testCertificateDateOfExpiryIsFutureDate() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setDateOfExpiry(LocalDate.of(2021, 10, 10));
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void skills_testCertificatePassingScoreNotNull() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setPassingScore(null);
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void skills_testCertificateMaxScoreNotNull() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setMaxScore(null);
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void skills_testCertificateScorePercentageNotNull() throws Exception {
		CertificatesDto certificatesDto = MasterData.getCertificatesDto();
		certificatesDto.setScorePercentage(null);
		Set<ConstraintViolation<CertificatesDto>> violations = validator.validate(certificatesDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void skills_testSkillsEmployeeIdNotNull() throws Exception {
		SkillsDto skillsDto = MasterData.getSkillsDto();
		skillsDto.setEmployeeId(null);
		Set<ConstraintViolation<SkillsDto>> violations = validator.validate(skillsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void skills_testSkillNameNotNull() throws Exception {
		SkillsDto skillsDto = MasterData.getSkillsDto();
		skillsDto.setName(null);
		Set<ConstraintViolation<SkillsDto>> violations = validator.validate(skillsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void skills_testSkillNameMinThree() throws Exception {
		SkillsDto skillsDto = MasterData.getSkillsDto();
		skillsDto.setName("Ab");
		Set<ConstraintViolation<SkillsDto>> violations = validator.validate(skillsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void skills_testSkillNameMaxHundered() throws Exception {
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
	public void skills_testSkillDescriptionNotNull() throws Exception {
		SkillsDto skillsDto = MasterData.getSkillsDto();
		skillsDto.setDescription(null);
		Set<ConstraintViolation<SkillsDto>> violations = validator.validate(skillsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void skills_testSkillDescriptionMinThree() throws Exception {
		SkillsDto skillsDto = MasterData.getSkillsDto();
		skillsDto.setDescription("Ab");
		Set<ConstraintViolation<SkillsDto>> violations = validator.validate(skillsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void skills_testSkillDescriptionMaxHundered() throws Exception {
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
	public void skills_testSkillProfeciencyLevelNotNull() throws Exception {
		SkillsDto skillsDto = MasterData.getSkillsDto();
		skillsDto.setProfeciencyLevel(null);
		Set<ConstraintViolation<SkillsDto>> violations = validator.validate(skillsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void skills_testSkillProfeciencyLevelMinThree() throws Exception {
		SkillsDto skillsDto = MasterData.getSkillsDto();
		skillsDto.setProfeciencyLevel("Ab");
		Set<ConstraintViolation<SkillsDto>> violations = validator.validate(skillsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void skills_testSkillProfeciencyLevelMaxHundered() throws Exception {
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
	public void skills_testSkillYearsOfExperienceNotNull() throws Exception {
		SkillsDto skillsDto = MasterData.getSkillsDto();
		skillsDto.setYearsOfExperience(null);
		Set<ConstraintViolation<SkillsDto>> violations = validator.validate(skillsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void skills_testSkillYearsOfExperienceMinThree() throws Exception {
		SkillsDto skillsDto = MasterData.getSkillsDto();
		skillsDto.setYearsOfExperience("Ab");
		Set<ConstraintViolation<SkillsDto>> violations = validator.validate(skillsDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void skills_testSkillYearsOfExperienceMaxHundered() throws Exception {
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
