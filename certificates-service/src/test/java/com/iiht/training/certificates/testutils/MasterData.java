package com.iiht.training.certificates.testutils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.iiht.training.certificates.dto.CertificatesDto;

public class MasterData {
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
