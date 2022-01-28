package com.iiht.training.certificates.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.iiht.training.certificates.dto.CertificatesDto;
import com.iiht.training.certificates.exceptions.InvalidDataException;
import com.iiht.training.certificates.service.CertificatesService;

@RestController
@RequestMapping("/api")
public class CertificatesRestController {

	@Autowired
	private CertificatesService certificatesService;

	@PostMapping("/certificates")
	public ResponseEntity<CertificatesDto> addCertificate(@Valid @RequestBody CertificatesDto certificatesDto,
			BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException("Certificate Input data is not valid");
		}
		int percent = (certificatesDto.getPassingScore() * 100) / certificatesDto.getMaxScore();
		certificatesDto.setScorePercentage(percent);
		certificatesService.generateCertificate(certificatesDto);
		return ResponseEntity.ok(certificatesDto);
	}

	@PutMapping("/certificates")
	public ResponseEntity<CertificatesDto> updateCertificate(@Valid @RequestBody CertificatesDto certificatesDto,
			BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException("Certificate Input data is not valid");
		}
		int percent = (certificatesDto.getPassingScore() * 100) / certificatesDto.getMaxScore();
		certificatesDto.setScorePercentage(percent);
		certificatesService.updateCertificate(certificatesDto);
		return ResponseEntity.ok(certificatesDto);
	}

	@DeleteMapping("/certificates/{id}")
	public ResponseEntity<Boolean> deleteCertificateById(@PathVariable("id") Integer id) {
		certificatesService.deleteCertificate(id);
		return ResponseEntity.ok(true);
	}

	// get certificates by certificate id
	@GetMapping("/certificates/{id}")
	public ResponseEntity<CertificatesDto> getCertificateById(@PathVariable("id") Integer id) {
		CertificatesDto certificatesDto = certificatesService.getById(id);
		return new ResponseEntity<CertificatesDto>(certificatesDto, HttpStatus.FOUND);
	}

	// get All certificates
	@GetMapping("/certificates")
	public ResponseEntity<List<CertificatesDto>> getAllCertificates() {
		List<CertificatesDto> list = certificatesService.findAll();
		return ResponseEntity.ok(list);
	}

	// get certificates by employeeId
	@GetMapping("/certificates/employee/{employeeId}")
	public ResponseEntity<List<CertificatesDto>> getCertificatesByEmployeeId(@PathVariable Integer employeeId) {
		List<CertificatesDto> certificates = certificatesService.findCertificatesByEmployeeId(employeeId);
		return ResponseEntity.ok(certificates);
	}

	// get certificates by skillName
	@GetMapping("/certificates/skills/{skillName}")
	public ResponseEntity<List<CertificatesDto>> getCertificatesBySkillName(@PathVariable String skillName) {
		List<CertificatesDto> certificates = certificatesService.findCertificatesBySkillName(skillName);
		return ResponseEntity.ok(certificates);
	}

}
