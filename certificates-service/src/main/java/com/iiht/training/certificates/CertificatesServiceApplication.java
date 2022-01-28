package com.iiht.training.certificates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CertificatesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CertificatesServiceApplication.class, args);
	}

}
