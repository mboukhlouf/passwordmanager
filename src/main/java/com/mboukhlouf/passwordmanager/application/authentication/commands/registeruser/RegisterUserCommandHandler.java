package com.mboukhlouf.passwordmanager.application.authentication.commands.registeruser;

import com.mboukhlouf.passwordmanager.application.common.abstractions.services.PasswordHasher;
import com.mboukhlouf.passwordmanager.application.common.exceptions.BadRequestException;
import com.mboukhlouf.passwordmanager.application.jmediatr.RequestHandler;
import com.mboukhlouf.passwordmanager.domain.entities.User;
import com.mboukhlouf.passwordmanager.persistence.repositories.UserRepository;

public class RegisterUserCommandHandler implements RequestHandler<RegisterUserCommand, Integer> {
    private UserRepository userRepository;
    private PasswordHasher passwordHasher;
    
    public RegisterUserCommandHandler(UserRepository userRepository, PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public Integer Handle(RegisterUserCommand request) throws BadRequestException {
        if(userRepository.findByUsername(request.getUsername()) != null) {
            throw new BadRequestException("The username is already taken.");
        }
        String passwordHash = passwordHasher.HashPassword(request.getPassword());
        User user = new User(request.getUsername(), passwordHash);
        userRepository.save(user);
        return user.getId();
    }
}