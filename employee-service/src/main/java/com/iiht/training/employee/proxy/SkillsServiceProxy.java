package com.iiht.training.employee.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.iiht.training.employee.dto.SkillsDto;

@FeignClient(name = "skills-service")
public interface SkillsServiceProxy {

	@GetMapping("/api/skills/by/{employeeId}")
	public List<SkillsDto> skillsByEmployeeId(@PathVariable("employeeId") Integer id);
	
	
}
