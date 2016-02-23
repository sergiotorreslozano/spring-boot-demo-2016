package com.st;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootHerokuApplication {

	private final static Logger logger = LoggerFactory.getLogger(SpringBootHerokuApplication.class);

	public static void main(String[] args) {
		logger.debug("Starting SpringBootHerokuApplication...");
		SpringApplication.run(SpringBootHerokuApplication.class, args);
		logger.debug("Configuration finished for SpringBootHerokuApplication ");

	}
}
