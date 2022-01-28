package com.iiht.training.skills.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.skills.dto.SkillsDto;
import com.iiht.training.skills.entity.Skills;
import com.iiht.training.skills.exceptions.SkillNotFoundException;
import com.iiht.training.skills.repository.SkillsRepository;
import com.iiht.training.skills.service.SkillsService;

@Service
public class SkillsServiceImpl implements SkillsService {

	@Autowired
	private SkillsRepository skillsRepository;

	@Override
	public SkillsDto addSkill(SkillsDto skillsDto) {
		Skills skills = new Skills();
		BeanUtils.copyProperties(skillsDto, skills);
		skillsRepository.save(skills);
		return skillsDto;
	}

	@Override
	public SkillsDto updateSkill(SkillsDto skillsDto) {
		Skills skills = new Skills();
		BeanUtils.copyProperties(skillsDto, skills);
		skillsRepository.save(skills);
		return skillsDto;
	}

	@Override
	public Boolean deleteSkill(Integer id) {
		SkillsDto skillsDto = getSkillById(id);
		Skills skills = new Skills();
		BeanUtils.copyProperties(skillsDto, skills);
		skillsRepository.delete(skills);
		return true;
	}

	@Override
	public SkillsDto getSkillById(Integer id) {
		Optional<Skills> findById = skillsRepository.findById(id);
		if (findById.isPresent()) {
			SkillsDto dto = new SkillsDto();
			BeanUtils.copyProperties(findById.get(), dto);
			return dto;
		}
		throw new SkillNotFoundException("Skill with id " + id + " does not exists");
	}

	@Override
	public List<SkillsDto> findAll() {
		List<Skills> list = skillsRepository.findAll();
		List<SkillsDto> skillsDtos = new ArrayList<>();
		for (Skills skill : list) {
			SkillsDto dto = new SkillsDto();
			BeanUtils.copyProperties(skill, dto);
			skillsDtos.add(dto);
		}
		return skillsDtos;
	}

	@Override
	public List<SkillsDto> skillsByEmployeeId(Integer employeeId) {
		List<Skills> list = skillsRepository.findByEmployeeId(employeeId);
		List<SkillsDto> skillsDtos = new ArrayList<>();
		for (Skills skill : list) {
			SkillsDto dto = new SkillsDto();
			BeanUtils.copyProperties(skill, dto);
			skillsDtos.add(dto);
		}
		return skillsDtos;
	}

}
