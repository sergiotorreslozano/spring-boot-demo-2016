package com.st.configuration;


import java.net.URI;
import java.net.URISyntaxException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

	private final static Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);

	@Bean
	public DataSource postgresDataSource() {
		String databaseUrl = System.getenv("DATABASE_URL");
		logger.debug("Found environment variable DATABASE_URL with value: " + databaseUrl);
		URI dbUri;
		try {
			dbUri = new URI(databaseUrl);
		} catch (URISyntaxException e) {
			logger.error("databaseUrl cannot be null");
			return null;
		}

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

		logger.debug("Data base properties");
		logger.debug("dbUrl: " + dbUrl);
		logger.debug("username: " + username);

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