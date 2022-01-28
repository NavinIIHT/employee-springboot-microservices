package com.iiht.training.certificates.dto;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CertificatesDto {

	private Integer id;
	@NotNull
	private Integer employeeId;
	@NotNull
	@Length(min = 3, max = 50)
	private String skillName;
	@NotNull
	@Length(min = 3, max = 100)
	private String description;
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent
	private LocalDate dateOfIssue;
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Future
	private LocalDate dateOfExpiry;
	@NotNull
	private Integer passingScore;
	@NotNull
	private Integer maxScore;
	private Integer scorePercentage;

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

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(LocalDate dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public LocalDate getDateOfExpiry() {
		return dateOfExpiry;
	}

	public void setDateOfExpiry(LocalDate dateOfExpiry) {
		this.dateOfExpiry = dateOfExpiry;
	}

	public Integer getPassingScore() {
		return passingScore;
	}

	public void setPassingScore(Integer passingScore) {
		this.passingScore = passingScore;
	}

	public Integer getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(Integer maxScore) {
		this.maxScore = maxScore;
	}

	public Integer getScorePercentage() {
		return scorePercentage;
	}

	public void setScorePercentage(Integer scorePercentage) {
		this.scorePercentage = scorePercentage;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateOfExpiry, dateOfIssue, description, employeeId, id, maxScore, passingScore,
				scorePercentage, skillName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CertificatesDto other = (CertificatesDto) obj;
		return Objects.equals(dateOfExpiry, other.dateOfExpiry) && Objects.equals(dateOfIssue, other.dateOfIssue)
				&& Objects.equals(description, other.description) && Objects.equals(employeeId, other.employeeId)
				&& Objects.equals(id, other.id) && Objects.equals(maxScore, other.maxScore)
				&& Objects.equals(passingScore, other.passingScore)
				&& Objects.equals(scorePercentage, other.scorePercentage) && Objects.equals(skillName, other.skillName);
	}
	
	
}
