package com.mboukhlouf.passwordmanager.webui;

import com.mboukhlouf.passwordmanager.application.jmediatr.Mediator;
import com.mboukhlouf.passwordmanager.application.test.HelloWorldQuery;
import com.mboukhlouf.passwordmanager.application.test.HelloWorldQueryHandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.mboukhlouf.passwordmanager.domain.entities")
@EnableJpaRepositories("com.mboukhlouf.passwordmanager.persistence.repositories")
public class PasswordmanagerApplication {

	public static void main(String[] args) {
		InitializeRequests();

		SpringApplication.run(PasswordmanagerApplication.class, args);
	}

	private static void InitializeRequests() {
		Mediator.AddRequest(HelloWorldQuery.class, new HelloWorldQueryHandler());
	}
}
