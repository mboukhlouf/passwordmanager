package com.mboukhlouf.passwordmanager.persistence.repositories;

import com.mboukhlouf.passwordmanager.domain.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    
}