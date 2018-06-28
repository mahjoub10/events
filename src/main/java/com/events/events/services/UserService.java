package com.events.events.services;

import com.events.events.mapper.AttendeeMapper;
import com.events.events.mapper.SpeakerMapper;
import com.events.events.mapper.UserMapper;
import com.events.events.models.Attendee;
import com.events.events.models.Speaker;
import com.events.events.models.User;
import com.events.events.repositories.UserRepository;
import com.events.events.security.SecurityUtils;
import com.events.events.web.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private Logger logger = LoggerFactory.getLogger(UserService.class);


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SpeakerMapper speakerMapper;

    @Autowired
    private AttendeeMapper attendeeMapper;

    /**
     * Get current account
     *
     * @return
     */
    public UserDto getAccount() {
        String email = SecurityUtils.getCurrentUserLogin();

        User user = userRepository
                .findOneByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User nto found"));

        if (user instanceof Speaker) {
            Speaker speaker = (Speaker) user;
            return speakerMapper.fromSpeakerToSpeakerDto(speaker);
        } else if (user instanceof Attendee) {
            Attendee attendee = (Attendee) user;
            return attendeeMapper.fromAttendeeToAttendeeDto(attendee);
        }

        else {
            return userMapper.fromUserToUserDto(user);
        }

    }

    /**
     * Get current usre
     *
     * @return
     */
    public User getCurrentUser() {
        String email = SecurityUtils.getCurrentUserLogin();

        return userRepository
                .findOneByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User nto found"));

    }


}
