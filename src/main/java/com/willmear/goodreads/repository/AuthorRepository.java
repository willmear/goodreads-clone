package com.willmear.goodreads.repository;

import com.willmear.goodreads.domain.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
