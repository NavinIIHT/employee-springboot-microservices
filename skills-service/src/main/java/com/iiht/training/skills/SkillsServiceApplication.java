package com.iiht.training.skills;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SkillsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkillsServiceApplication.class, args);
	}

}
