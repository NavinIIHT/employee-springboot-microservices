package com.iiht.training.skills.service;

import java.util.List;

import com.iiht.training.skills.dto.SkillsDto;

public interface SkillsService {

	public SkillsDto addSkill(SkillsDto skillsDto);
	public SkillsDto updateSkill(SkillsDto skillsDto);
	public Boolean deleteSkill(Integer id);
	public SkillsDto getSkillById(Integer id);
	public List<SkillsDto> findAll();
	public List<SkillsDto> skillsByEmployeeId(Integer emloyeeId);
	
}
