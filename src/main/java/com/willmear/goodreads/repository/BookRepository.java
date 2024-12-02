package com.willmear.goodreads.repository;

import com.willmear.goodreads.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
