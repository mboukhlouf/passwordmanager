package com.mboukhlouf.passwordmanager.persistence.repositories;

import com.mboukhlouf.passwordmanager.domain.entities.Password;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRepository extends JpaRepository<Password, Integer> {
    
}