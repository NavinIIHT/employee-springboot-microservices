package com.iiht.training.skills.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
import org.springframework.web.client.RestTemplate;

import com.iiht.training.skills.dto.CertificatesDto;
import com.iiht.training.skills.dto.SkillsDto;
import com.iiht.training.skills.exceptions.InvalidDataException;
import com.iiht.training.skills.proxy.CertificatesServiceProxy;
import com.iiht.training.skills.service.SkillsService;

@RestController
@RequestMapping("/api")
public class SkillsRestController {

	@Autowired
	private Environment environment;

	@Autowired
	private SkillsService skillsService;

	@Autowired
	private CertificatesServiceProxy proxy;

	@PostMapping("/skills")
	public ResponseEntity<SkillsDto> addSkill(@Valid @RequestBody SkillsDto skillsDto, BindingResult result) {

		if (result.hasErrors()) {
			throw new InvalidDataException("Skill Data is not valid");
		}
		SkillsDto skill = skillsService.addSkill(skillsDto);
		return ResponseEntity.ok(skill);
	}

	@PutMapping("/skills")
	public ResponseEntity<SkillsDto> updateSkill(@Valid @RequestBody SkillsDto skillsDto, BindingResult result) {

		if (result.hasErrors()) {
			throw new InvalidDataException("Skill Data is not valid");
		}
		SkillsDto skill = skillsService.updateSkill(skillsDto);
		return ResponseEntity.ok(skill);
	}

	@DeleteMapping("/skills/{id}")
	public ResponseEntity<Boolean> deleteSkillsById(@PathVariable("id") Integer id) {
		skillsService.deleteSkill(id);
		return ResponseEntity.ok(true);

	}

	@GetMapping("/skills")
	public ResponseEntity<List<SkillsDto>> findAllSkills() {
		List<SkillsDto> list = skillsService.findAll();
		String port = environment.getProperty("local.server.port");
		List<SkillsDto> skills = list.stream().map(s -> {
			s.setEnvironment(port);
			return s;
		}).collect(Collectors.toList());
		return ResponseEntity.ok(skills);
	}

	@GetMapping("/skills/{id}")
	public ResponseEntity<SkillsDto> skillsById(@PathVariable("id") Integer id) {
		SkillsDto skill = skillsService.getSkillById(id);
		String port = environment.getProperty("local.server.port");
		skill.setEnvironment(port);
		return new ResponseEntity<SkillsDto>(skill, HttpStatus.FOUND);
	}

	@GetMapping("/skills/by/{employeeId}")
	public ResponseEntity<List<SkillsDto>> skillsByEmployeeId(@PathVariable("employeeId") Integer id) {
		List<SkillsDto> skills = skillsService.skillsByEmployeeId(id);

		String port = environment.getProperty("local.server.port");
		for (SkillsDto skill : skills) {
			skill.setEnvironment(port);
		}
		return ResponseEntity.ok(skills);
	}

	@GetMapping("/skills/certificates-by-{name}")
	public List<CertificatesDto> getCertificatesBySkillName(@PathVariable String name) {

		HashMap<String, String> uriVariables = new HashMap<>();
		uriVariables.put("name", name);
		CertificatesDto[] array = new RestTemplate().getForObject(
				"http://localhost:9000/api/certificates/skills/{name}", CertificatesDto[].class, uriVariables);
		return Arrays.asList(array);
	}

	@GetMapping("/skills/certificates-by-skill-name/{name}")
	public ResponseEntity<List<CertificatesDto>> getCertificatesBySkillNameFeign(@PathVariable String name) {
		List<CertificatesDto> list = proxy.getCertificatesBySkillName(name);
		return ResponseEntity.ok(list);
	}

}
