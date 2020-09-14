package com.mboukhlouf.passwordmanager.webui;

import com.mboukhlouf.passwordmanager.application.authentication.commands.authenticateuser.*;
import com.mboukhlouf.passwordmanager.application.authentication.commands.registeruser.*;
import com.mboukhlouf.passwordmanager.application.common.abstractions.services.PasswordHasher;
import com.mboukhlouf.passwordmanager.application.jmediatr.Mediator;
import com.mboukhlouf.passwordmanager.infrastructure.services.BCryptHasher;
import com.mboukhlouf.passwordmanager.persistence.repositories.UserRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public PasswordHasher passwordHasher() {
        return new BCryptHasher();
    }

    @Bean
    public Mediator mediator() {
        return new Mediator();
    }

    @Bean
    public AuthenticateUserCommandHandler authenticateUserCommandHandler(UserRepository userRepository, 
    PasswordHasher passwordHasher) {
        AuthenticateUserCommandHandler handler = new AuthenticateUserCommandHandler(userRepository, passwordHasher);
        Mediator.AddRequest(AuthenticateUserCommand.class, handler);
        return handler;
    }

    @Bean
    public RegisterUserCommandHandler registerUserCommandHandler(UserRepository userRepository, 
    PasswordHasher passwordHasher) {
        RegisterUserCommandHandler handler = new RegisterUserCommandHandler(userRepository, passwordHasher);
        Mediator.AddRequest(RegisterUserCommand.class, handler);
        return handler;
    }
}
