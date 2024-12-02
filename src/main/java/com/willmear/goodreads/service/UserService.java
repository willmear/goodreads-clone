package com.willmear.goodreads.service;

import com.willmear.goodreads.domain.entity.User;
import com.willmear.goodreads.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ResponseEntity<User> getUser(Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
            String email = oauth2User.getAttribute("email");
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

//        User user = userRepository.findById(id).orElse(null);
//        if (user != null) {
//            return ResponseEntity.ok(user);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
    }
}
