package com.willmear.goodreads.service;

import com.willmear.goodreads.domain.entity.Book;
import com.willmear.goodreads.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public ResponseEntity<Book> getBook(Long id) {

        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
