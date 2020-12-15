package com.VaccNow.vaccine.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.VaccNow.vaccine"})
@EntityScan("com.VaccNow.vaccine.model")
@EnableJpaRepositories("com.VaccNow.vaccine.repository")
public class VaccineApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(VaccineApplication.class, args);
	}

}
