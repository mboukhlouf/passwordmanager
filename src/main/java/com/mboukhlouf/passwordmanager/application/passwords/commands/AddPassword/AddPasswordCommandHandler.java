package com.mboukhlouf.passwordmanager.application.passwords.commands.AddPassword;

import com.mboukhlouf.passwordmanager.application.common.abstractions.services.ICurrentUserService;
import com.mboukhlouf.passwordmanager.application.common.abstractions.services.IUserPasswordCrypter;
import com.mboukhlouf.passwordmanager.application.common.exceptions.UnauthorizedException;
import com.mboukhlouf.passwordmanager.application.jmediatr.RequestHandler;
import com.mboukhlouf.passwordmanager.domain.entities.Password;
import com.mboukhlouf.passwordmanager.domain.entities.User;
import com.mboukhlouf.passwordmanager.persistence.repositories.PasswordRepository;
import com.mboukhlouf.passwordmanager.persistence.repositories.UserRepository;

public class AddPasswordCommandHandler implements RequestHandler<AddPasswordCommand, Integer> {
    private final UserRepository userRepository;
    private final PasswordRepository passwordRepository;
    private final ICurrentUserService currentUserService;
    private final IUserPasswordCrypter userPasswordCrypter;

    public AddPasswordCommandHandler(UserRepository userRepository, 
    PasswordRepository passwordRepository,
    ICurrentUserService currentUserService,
    IUserPasswordCrypter userPasswordCrypter) {
        this.userRepository = userRepository;
        this.passwordRepository = passwordRepository;
        this.currentUserService = currentUserService;
        this.userPasswordCrypter = userPasswordCrypter;
    }

    @Override
    public Integer Handle(AddPasswordCommand request) throws Exception {
        if(!currentUserService.isAuthenticated()) {
            throw new UnauthorizedException("Unauthorized.");
        }

        User user = userRepository.findById(currentUserService.getId()).get();
        Password password = new Password();
        password.setLabel(request.getLabel());
        password.setAccount(request.getAccount());
        String passwordEnrypted = userPasswordCrypter.encrypt(request.getPassword(), currentUserService.getPassword());
        password.setPassword(passwordEnrypted);
        password.setUrl(request.getUrl());
        password.setTags(request.getTags());
        password.setUser(user);

        passwordRepository.save(password);
        return password.getId();
    }
    
}
