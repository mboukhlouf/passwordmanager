package com.mboukhlouf.passwordmanager.application.passwords.queries.GetUserPasswords;

import java.util.ArrayList;
import java.util.List;

import com.mboukhlouf.passwordmanager.application.common.abstractions.services.ICurrentUserService;
import com.mboukhlouf.passwordmanager.application.common.abstractions.services.IUserPasswordCrypter;
import com.mboukhlouf.passwordmanager.application.common.exceptions.UnauthorizedException;
import com.mboukhlouf.passwordmanager.application.jmediatr.RequestHandler;
import com.mboukhlouf.passwordmanager.domain.entities.Password;
import com.mboukhlouf.passwordmanager.domain.entities.User;
import com.mboukhlouf.passwordmanager.persistence.repositories.UserRepository;

public class GetUserPasswordsQueryHandler implements RequestHandler<GetUserPasswordsQuery, Iterable<UserPasswordDto>> {
    private final UserRepository userRepository;
    private final ICurrentUserService currentUserService;
    private final IUserPasswordCrypter userPasswordCrypter;
    
    public GetUserPasswordsQueryHandler(UserRepository userRepository, ICurrentUserService currentUserService,
            IUserPasswordCrypter userPasswordCrypter) {
        this.userRepository = userRepository;
        this.currentUserService = currentUserService;
        this.userPasswordCrypter = userPasswordCrypter;
    }

    @Override
    public Iterable<UserPasswordDto> Handle(GetUserPasswordsQuery request) throws Exception {
        if(!currentUserService.isAuthenticated()) {
            throw new UnauthorizedException("Unauthorized.");
        }

        User user = userRepository.findById(currentUserService.getId()).get();
        List<Password> passwords = user.getPasswords();
        List<UserPasswordDto> passwordsDtos = new ArrayList<UserPasswordDto>();
        for(Password password : passwords) {
            String passwordDecrypted = userPasswordCrypter.decrypt(password.getPassword(), currentUserService.getPassword());
            UserPasswordDto passwordDto = new UserPasswordDto(password.getId(), password.getLabel(), 
            password.getAccount(), passwordDecrypted, password.getUrl(), password.getTags(), password.getUser().getId());
            passwordsDtos.add(passwordDto);
        }
        return passwordsDtos;
    }
}
