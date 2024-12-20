package com.willmear.goodreads.controller;

import com.willmear.goodreads.domain.entity.Book;
import com.willmear.goodreads.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        return bookService.getBook(id);
    }

    @GetMapping("/search/{searchText}")
    public ResponseEntity<List<Book>> searchBooks(@PathVariable String searchText) {
        return bookService.searchBooks(searchText);
    }

    @GetMapping("/upload")
    public void upload() {
        bookService.upload();
    }

}
