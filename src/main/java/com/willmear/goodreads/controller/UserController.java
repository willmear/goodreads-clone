package com.willmear.goodreads.controller;

import com.willmear.goodreads.domain.entity.User;
import com.willmear.goodreads.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    ResponseEntity<User> getUser(@PathVariable Long id) {

        return userService.getUser(id);

    }

}
