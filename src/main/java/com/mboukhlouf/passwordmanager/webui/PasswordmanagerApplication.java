package com.mboukhlouf.passwordmanager.webui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.mboukhlouf.passwordmanager.domain.entities")
@EnableJpaRepositories("com.mboukhlouf.passwordmanager.persistence.repositories")
public class PasswordmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PasswordmanagerApplication.class, args);
	}
}
