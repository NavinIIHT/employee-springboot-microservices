package com.iiht.training.skills.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.iiht.training.skills.dto.CertificatesDto;

@FeignClient(name = "certificates-service")
public interface CertificatesServiceProxy {

	@GetMapping("/api/certificates/skills/{skillName}")
	public List<CertificatesDto> getCertificatesBySkillName(@PathVariable String skillName); 
}
