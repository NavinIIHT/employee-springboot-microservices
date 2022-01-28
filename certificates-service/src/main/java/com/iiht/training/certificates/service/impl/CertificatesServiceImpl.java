package com.iiht.training.certificates.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.certificates.dto.CertificatesDto;
import com.iiht.training.certificates.exceptions.CertificateNotFoundException;
import com.iiht.training.certificates.model.Certificates;
import com.iiht.training.certificates.repository.CertificatesRepository;
import com.iiht.training.certificates.service.CertificatesService;

@Service
public class CertificatesServiceImpl implements CertificatesService {

	@Autowired
	private CertificatesRepository repository;

	@Override
	public CertificatesDto generateCertificate(CertificatesDto certificatesDto) {
		Certificates certificate = new Certificates();
		BeanUtils.copyProperties(certificatesDto, certificate);
		repository.save(certificate);
		return certificatesDto;
	}

	@Override
	public CertificatesDto updateCertificate(CertificatesDto certificatesDto) {
		Certificates certificate = new Certificates();
		BeanUtils.copyProperties(certificatesDto, certificate);
		repository.save(certificate);
		return certificatesDto;
	}

	@Override
	public Boolean deleteCertificate(Integer id) {
		CertificatesDto certificatesDto = getById(id);
		Certificates certificates = new Certificates();
		BeanUtils.copyProperties(certificatesDto, certificates);
		repository.delete(certificates);
		return true;
	}

	@Override
	public CertificatesDto getById(Integer id) {
		Optional<Certificates> findById = repository.findById(id);
		if (findById.isPresent()) {
			CertificatesDto certificatesDto = new CertificatesDto();
			BeanUtils.copyProperties(findById.get(), certificatesDto);
			return certificatesDto;
		}
		throw new CertificateNotFoundException("Certificate with id" + id + " does not exists");
	}

	@Override
	public List<CertificatesDto> findAll() {
		List<Certificates> list = repository.findAll();
		List<CertificatesDto> dtos = new ArrayList<>();
		for (Certificates certificates : list) {
			CertificatesDto dto = new CertificatesDto();
			BeanUtils.copyProperties(certificates, dto);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<CertificatesDto> findCertificatesByEmployeeId(Integer id) {
		List<Certificates> list = repository.findByEmployeeId(id);
		List<CertificatesDto> dtos = new ArrayList<>();
		for (Certificates certificates : list) {
			CertificatesDto dto = new CertificatesDto();
			BeanUtils.copyProperties(certificates, dto);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<CertificatesDto> findCertificatesBySkillName(String skillName) {
		List<Certificates> list = repository.findBySkillName(skillName);
		List<CertificatesDto> dtos = new ArrayList<>();
		for (Certificates certificates : list) {
			CertificatesDto dto = new CertificatesDto();
			BeanUtils.copyProperties(certificates, dto);
			dtos.add(dto);
		}
		return dtos;
	}

}
