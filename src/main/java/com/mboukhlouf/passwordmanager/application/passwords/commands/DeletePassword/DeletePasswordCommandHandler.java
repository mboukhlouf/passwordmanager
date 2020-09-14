package com.mboukhlouf.passwordmanager.application.passwords.commands.DeletePassword;

import java.util.Optional;

import com.mboukhlouf.passwordmanager.application.common.abstractions.services.ICurrentUserService;
import com.mboukhlouf.passwordmanager.application.common.exceptions.NotFoundException;
import com.mboukhlouf.passwordmanager.application.common.exceptions.UnauthorizedException;
import com.mboukhlouf.passwordmanager.application.jmediatr.RequestHandler;
import com.mboukhlouf.passwordmanager.domain.entities.Password;
import com.mboukhlouf.passwordmanager.domain.entities.User;
import com.mboukhlouf.passwordmanager.persistence.repositories.PasswordRepository;
import com.mboukhlouf.passwordmanager.persistence.repositories.UserRepository;

public class DeletePasswordCommandHandler implements RequestHandler<DeletePasswordCommand, Integer> {
    private final ICurrentUserService currentUserService;
    private final UserRepository userRepository;
    private final PasswordRepository passwordRepository;
    
    public DeletePasswordCommandHandler(ICurrentUserService currentUserService, UserRepository userRepository,
    PasswordRepository passwordRepository) {
        this.currentUserService = currentUserService;
        this.userRepository = userRepository;
        this.passwordRepository = passwordRepository;
    }

    @Override
    public Integer Handle(DeletePasswordCommand request) throws Exception {
        if(!currentUserService.isAuthenticated()) {
            throw new UnauthorizedException("Unauthorized.");
        }

        Optional<Password> password = passwordRepository.findById(request.getPasswordId());
        if(!password.isPresent()) {
            throw new NotFoundException("Password not found.");
        }

        User user = userRepository.findById(currentUserService.getId()).get();
        if(!user.getPasswords().contains(password.get())) {
            throw new NotFoundException("Password not found.");
        }

        passwordRepository.delete(password.get());
        return 0;
    }
}
