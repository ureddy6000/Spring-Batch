package com.assignment.batch.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class })
public class SpringBootBatchMultipleCsvApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBatchMultipleCsvApplication.class, args);
	}
}
