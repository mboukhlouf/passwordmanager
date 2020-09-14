package com.mboukhlouf.passwordmanager.webui;

import com.mboukhlouf.passwordmanager.application.authentication.commands.authenticateuser.*;
import com.mboukhlouf.passwordmanager.application.authentication.commands.registeruser.*;
import com.mboukhlouf.passwordmanager.application.common.abstractions.services.ICurrentUserService;
import com.mboukhlouf.passwordmanager.application.common.abstractions.services.IUserPasswordCrypter;
import com.mboukhlouf.passwordmanager.application.common.abstractions.services.PasswordHasher;
import com.mboukhlouf.passwordmanager.application.jmediatr.Mediator;
import com.mboukhlouf.passwordmanager.application.passwords.commands.AddPassword.*;
import com.mboukhlouf.passwordmanager.application.passwords.commands.DeletePassword.DeletePasswordCommand;
import com.mboukhlouf.passwordmanager.application.passwords.commands.DeletePassword.DeletePasswordCommandHandler;
import com.mboukhlouf.passwordmanager.application.passwords.queries.GetUserPasswords.GetUserPasswordsQuery;
import com.mboukhlouf.passwordmanager.application.passwords.queries.GetUserPasswords.GetUserPasswordsQueryHandler;
import com.mboukhlouf.passwordmanager.infrastructure.services.AesCrypter;
import com.mboukhlouf.passwordmanager.infrastructure.services.BCryptHasher;
import com.mboukhlouf.passwordmanager.persistence.repositories.PasswordRepository;
import com.mboukhlouf.passwordmanager.persistence.repositories.UserRepository;
import com.mboukhlouf.passwordmanager.webui.services.CurrentUserService;

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

    @Bean
    public AddPasswordCommandHandler addPasswordCommandHandler(UserRepository userRepository, 
    PasswordRepository passwordRepository, ICurrentUserService currentUserService, IUserPasswordCrypter userPasswordCrypter) {
        AddPasswordCommandHandler handler = new AddPasswordCommandHandler(userRepository, passwordRepository, currentUserService, userPasswordCrypter);
        Mediator.AddRequest(AddPasswordCommand.class, handler);
        return handler;
    }

    @Bean
    public DeletePasswordCommandHandler deletePasswordCommandHandler(UserRepository userRepository, 
    PasswordRepository passwordRepository, ICurrentUserService currentUserService, IUserPasswordCrypter userPasswordCrypter) {
        DeletePasswordCommandHandler handler = new DeletePasswordCommandHandler(currentUserService, userRepository, passwordRepository);
        Mediator.AddRequest(DeletePasswordCommand.class, handler);
        return handler;
    }

    @Bean
    public GetUserPasswordsQueryHandler getUserPasswordsQueryHandler(UserRepository userRepository, 
    ICurrentUserService currentUserService, IUserPasswordCrypter userPasswordCrypter) {
        GetUserPasswordsQueryHandler handler = new GetUserPasswordsQueryHandler(userRepository, currentUserService, userPasswordCrypter);
        Mediator.AddRequest(GetUserPasswordsQuery.class, handler);
        return handler;
    }


    @Bean
    public ICurrentUserService currentUserService() {
        return new CurrentUserService();
    }

    @Bean
    public IUserPasswordCrypter userPasswordCrypter() {
        return new AesCrypter();
    }
}
