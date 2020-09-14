package com.mboukhlouf.passwordmanager.webui;

import org.springframework.web.bind.annotation.RestController;

import com.mboukhlouf.passwordmanager.application.authentication.commands.registeruser.RegisterUserCommand;
import com.mboukhlouf.passwordmanager.application.common.abstractions.services.PasswordHasher;
import com.mboukhlouf.passwordmanager.application.jmediatr.Mediator;
import com.mboukhlouf.passwordmanager.domain.entities.User;
import com.mboukhlouf.passwordmanager.persistence.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class HelloController {
	
	private final UserRepository userRepository;
	private final PasswordHasher passwordHasher;
	private final Mediator mediator;
	
	@Autowired
	public HelloController(UserRepository userRepository, 
	PasswordHasher passwordHasher, 
	Mediator mediator) {
		this.userRepository = userRepository;
		this.passwordHasher = passwordHasher;
		this.mediator = mediator;
	}

	@GetMapping
	public Integer Get() throws Exception {
		return mediator.Send(new RegisterUserCommand("mohammed", "gsdgsd"));
	}

	@PostMapping
	public String Post(User user) {
		userRepository.save(user);
		return "Done!";
	}
}