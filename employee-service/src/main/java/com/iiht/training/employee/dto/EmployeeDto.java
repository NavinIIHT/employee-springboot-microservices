package com.iiht.training.employee.dto;

import java.util.Objects;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class EmployeeDto {

	private Integer id;
	@NotNull
	@Length(min = 3, max = 50)
	@Column(unique = true)
	private String username;
	@NotNull
	@Length(min = 5, max = 30)
	private String password;
	@NotNull
	@Length(min = 3, max = 200)
	private String address;
	@NotNull
	@Min(1000000000)
	@Max(9999999999L)
	private Long phoneNumber;
	@NotNull
	private Double salary;
	@NotNull
	private Integer experience;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, experience, id, password, phoneNumber, salary, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeDto other = (EmployeeDto) obj;
		return Objects.equals(address, other.address) && Objects.equals(experience, other.experience)
				&& Objects.equals(id, other.id) && Objects.equals(password, other.password)
				&& Objects.equals(phoneNumber, other.phoneNumber) && Objects.equals(salary, other.salary)
				&& Objects.equals(username, other.username);
	}

}
