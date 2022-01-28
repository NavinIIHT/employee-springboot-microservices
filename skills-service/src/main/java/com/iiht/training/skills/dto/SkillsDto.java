package com.iiht.training.skills.dto;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class SkillsDto {

	private Integer id;
	@NotNull
	private Integer employeeId;
	@NotNull
	@Length(min = 3, max = 100)
	private String name;
	@NotNull
	@Length(min = 3, max = 100)
	private String description;
	@NotNull
	@Length(min = 3, max = 100)
	private String profeciencyLevel;
	@NotNull
	@Length(min = 3, max = 100)
	private String yearsOfExperience;
	private String environment;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProfeciencyLevel() {
		return profeciencyLevel;
	}

	public void setProfeciencyLevel(String profeciencyLevel) {
		this.profeciencyLevel = profeciencyLevel;
	}

	public String getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(String yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, employeeId, environment, id, name, profeciencyLevel, yearsOfExperience);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SkillsDto other = (SkillsDto) obj;
		return Objects.equals(description, other.description) && Objects.equals(employeeId, other.employeeId)
				&& Objects.equals(environment, other.environment) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(profeciencyLevel, other.profeciencyLevel)
				&& Objects.equals(yearsOfExperience, other.yearsOfExperience);
	}

	
}
