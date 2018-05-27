package com.events.events.security;

import com.events.events.models.User;
import com.events.events.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventUserDetailService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(EventUserDetailService.class);

    @Autowired
    private UserRepository userRepository ;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {

        logger.info("Authenticating the user {}", email);
        Optional<User> userFound = userRepository.findOneByEmail(email);
        return userFound.orElseThrow(() -> new UsernameNotFoundException("user " + email + " Not found in the database"));
    }
}
