package com.st.configuration;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DatabaseConfig {
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "SPRING_DATASOURCE")
	public DataSource dataSource() {
		// TODO: right now there are 2 configurations for the local environment
		// application.properties --> spring-boots reads from here
		// .env --> heroku local reads from here
		// The idea is to point Spring boot properties to .env
		return DataSourceBuilder.create().driverClassName(System.getenv("SPRING_DATASOURCE_DIRVER-CLASS-NAME"))
				.url(System.getenv("SPRING_DATASOURCE_URL")).username(System.getenv("SPRING_DATASOURCE_USERNAME"))
				.password(System.getenv("SPRING_DATASOURCE_PASSWORD")).build();
		// return DataSourceBuilder.create().build();
	}
}