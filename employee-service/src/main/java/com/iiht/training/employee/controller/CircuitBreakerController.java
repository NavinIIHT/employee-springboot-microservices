package com.iiht.training.employee.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
public class CircuitBreakerController {

	Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

	@GetMapping("/fallback-api")
//	@Retry(name = "fallback-api", fallbackMethod = "hardCodedMessage")
//	@CircuitBreaker(name = "default", fallbackMethod = "hardCodedMessage")
	@RateLimiter(name = "default")
	public String circuitBreakerAPI() {

		logger.info("Circuit Breaker API Call Recieved!");
//		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8888/some-dummy-url",
//				String.class);
//
//		return forEntity.getBody();
		return "Message from Fallback API";
	}

	public String hardCodedMessage(Exception ex) {
		return "This is Fallback Response";
	}
}
