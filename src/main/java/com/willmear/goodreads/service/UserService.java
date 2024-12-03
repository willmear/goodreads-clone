package com.willmear.goodreads.service;

import com.willmear.goodreads.domain.entity.Book;
import com.willmear.goodreads.domain.entity.User;
import com.willmear.goodreads.repository.BookRepository;
import com.willmear.goodreads.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;


    public ResponseEntity<User> getUser() {

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

    public ResponseEntity<User> wantToRead(Long bookId) {

        Book book = bookRepository.findById(bookId).orElse(null);

        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .build();
        }

        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        String email = oauth2User.getAttribute("email");

        User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

        List<Book> wantToRead = user.getWantToRead();

        if (wantToRead.contains(book)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        wantToRead.add(book);
        user.setWantToRead(wantToRead);

        return ResponseEntity.ok(userRepository.save(user));
    }

    public ResponseEntity<User> reading(Long bookId) {

        Book book = bookRepository.findById(bookId).orElse(null);

        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .build();
        }

        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        String email = oauth2User.getAttribute("email");

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Book> wantToRead = user.getWantToRead();
        List<Book> reading = user.getReading();

        if (reading.contains(book)) {
            ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        if (wantToRead.contains(book)) {
            wantToRead.remove(book);
            user.setWantToRead(wantToRead);
        }
        reading.add(book);
        user.setReading(reading);

        return ResponseEntity.ok(userRepository.save(user));

    }

    public ResponseEntity<User> read(Long bookId) {

        Book book = bookRepository.findById(bookId).orElse(null);

        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .build();
        }

        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        String email = oauth2User.getAttribute("email");

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Book> reading = user.getWantToRead();
        List<Book> read = user.getReading();

        if (read.contains(book)) {
            ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        if (reading.contains(book)) {
            reading.remove(book);
            user.setReading(reading);
        }
        read.add(book);
        user.setRead(read);

        return ResponseEntity.ok(userRepository.save(user));

    }
}
