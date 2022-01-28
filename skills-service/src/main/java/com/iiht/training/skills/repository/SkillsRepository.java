package com.iiht.training.skills.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iiht.training.skills.entity.Skills;

public interface SkillsRepository extends JpaRepository<Skills, Integer> {

	List<Skills> findByEmployeeId(Integer employeeId);
}
