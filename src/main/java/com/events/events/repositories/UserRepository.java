package com.events.events.repositories;

import com.events.events.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  UserRepository extends JpaRepository<User, Long> {

    /**
     * Find user by email.
     *
     * @param email
     * @return
     */
    Optional<User> findOneByEmail(String email);
}
