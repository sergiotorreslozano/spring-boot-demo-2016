package com.st.configuration;


import java.net.URI;
import java.net.URISyntaxException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

	// @Bean
	// @Primary
	// // @ConfigurationProperties(prefix = "SPRING_DATASOURCE")
	// public DataSource dataSource() {
	// // TODO: right now there are 2 configurations for the local environment
	// // application.properties --> spring-boots reads from here
	// // .env --> heroku local reads from here
	// // The idea is to point Spring boot properties to .env
	// String JDBC_DATABASE_URL = System.getenv().get("JDBC_DATABASE_URL");
	// System.out.println("JDBC_DATABASE_URL: " + JDBC_DATABASE_URL);
	// String driverClassName =
	// System.getenv().get("SPRING_DATASOURCE_DIRVER-CLASS-NAME");
	// String url = System.getenv("SPRING_DATASOURCE_URL");
	// String username = System.getenv("SPRING_DATASOURCE_USERNAME");
	// String password =System.getenv("SPRING_DATASOURCE_PASSWORD");
	// System.out.println("driverClassName: " + driverClassName);
	// System.out.println("url: " + url);
	// System.out.println("username: " + username);
	// System.out.println("password: " + password);
	//
	//
	// return (DataSource) DataSourceBuilder.create()
	// .driverClassName(System.getenv("SPRING_DATASOURCE_DIRVER-CLASS-NAME"))
	// .url(System.getenv("SPRING_DATASOURCE_URL")).username(System.getenv("SPRING_DATASOURCE_USERNAME"))
	// .password(System.getenv("SPRING_DATASOURCE_PASSWORD")).build();
	// // return DataSourceBuilder.create().build();
	// }

	@Bean
	public DataSource postgresDataSource() {
		String databaseUrl = System.getenv("DATABASE_URL");

		System.out.println("databaseUrl: " + databaseUrl);
		URI dbUri;
		try {
			dbUri = new URI(databaseUrl);
		} catch (URISyntaxException e) {
			return null;
		}

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

		System.out.println("dbUrl: " + dbUrl);

		org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl(dbUrl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setTestOnBorrow(true);
		dataSource.setTestWhileIdle(true);
		dataSource.setTestOnReturn(true);
		dataSource.setValidationQuery("SELECT 1");
		return dataSource;
	}

}