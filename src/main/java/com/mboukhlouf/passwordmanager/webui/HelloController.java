package com.mboukhlouf.passwordmanager.webui;

import org.springframework.web.bind.annotation.RestController;

import com.mboukhlouf.passwordmanager.domain.entities.User;
import com.mboukhlouf.passwordmanager.persistence.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class HelloController {
	
	private final UserRepository userRepository;

	@Autowired
	public HelloController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping
	public Iterable<User> Get() {
		return userRepository.findAll();
	}

	@PostMapping
	public String Post(User user) {
		userRepository.save(user);
		return "Done!";
	}
}