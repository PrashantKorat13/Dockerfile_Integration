package edu.springbootpostgresjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SpringBootPostgresJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPostgresJpaApplication.class, args);
	}

}
