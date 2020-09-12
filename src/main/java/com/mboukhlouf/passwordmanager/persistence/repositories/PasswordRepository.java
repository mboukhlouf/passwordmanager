package com.mboukhlouf.passwordmanager.persistence.repositories;

import com.mboukhlouf.passwordmanager.domain.entities.Password;
import org.springframework.data.repository.CrudRepository;

public interface PasswordRepository extends CrudRepository<Password, Integer> {
    
}