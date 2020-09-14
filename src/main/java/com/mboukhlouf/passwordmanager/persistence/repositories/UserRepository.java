package com.mboukhlouf.passwordmanager.persistence.repositories;

import com.mboukhlouf.passwordmanager.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}