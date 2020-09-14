package com.mboukhlouf.passwordmanager.application.authentication.commands.authenticateuser;

import com.mboukhlouf.passwordmanager.application.common.abstractions.services.PasswordHasher;
import com.mboukhlouf.passwordmanager.application.common.dtos.UserDto;
import com.mboukhlouf.passwordmanager.application.jmediatr.RequestHandler;
import com.mboukhlouf.passwordmanager.domain.entities.User;
import com.mboukhlouf.passwordmanager.persistence.repositories.UserRepository;


public class AuthenticateUserCommandHandler implements RequestHandler<AuthenticateUserCommand, UserDto> {
    private UserRepository userRepository;
    private PasswordHasher passwordHasher;
    
    public AuthenticateUserCommandHandler(UserRepository userRepository, PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public UserDto Handle(AuthenticateUserCommand request) {
        User user = userRepository.findByUsername(request.getUsername());
        if(user == null) {
            return null;
        }
        if(passwordHasher.Verify(request.getPassword(), user.getPasswordHash())) {
            return new UserDto(user.getId(), user.getUsername());
        }
        return null;
    }
}
