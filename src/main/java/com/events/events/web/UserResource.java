package com.events.events.web;

import com.events.events.services.UserService;
import com.events.events.web.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/user")
@RestController
public class UserResource {

    private Logger logger = LoggerFactory.getLogger(UserResource.class);

    @Autowired
    private UserService userService ;


    @GetMapping("/account")
    public ResponseEntity<UserDto> authenticate() {
        logger.info("Get current user");

        UserDto user = userService.getAccount();
        return ResponseEntity.ok(user);
    }
}
