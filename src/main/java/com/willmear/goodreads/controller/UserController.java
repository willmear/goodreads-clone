package com.willmear.goodreads.controller;

import com.willmear.goodreads.domain.entity.User;
import com.willmear.goodreads.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    ResponseEntity<User> getUser() {

        return userService.getUser();

    }

    @PostMapping("/want-to-read")
    ResponseEntity<User> wantToRead(@RequestParam Long bookId) {

        return userService.wantToRead(bookId);

    }

    @PostMapping("/currently-read")
    ResponseEntity<User> reading(@RequestParam Long bookId) {

        return userService.reading(bookId);

    }

    @PostMapping("/read")
    ResponseEntity<User> read(@RequestParam Long bookId) {

        return userService.read(bookId);

    }


}
