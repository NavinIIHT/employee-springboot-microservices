package com.iiht.training.certificates.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iiht.training.certificates.model.Certificates;

@Repository
public interface CertificatesRepository extends JpaRepository<Certificates, Integer> {

	List<Certificates> findByEmployeeId(Integer employeeId);

	List<Certificates> findBySkillName(String skillName);
	
}
