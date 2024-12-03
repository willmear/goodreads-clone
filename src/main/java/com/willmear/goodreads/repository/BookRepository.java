package com.willmear.goodreads.repository;

import com.willmear.goodreads.domain.entity.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b LEFT JOIN b.bookAuthor a WHERE " +
            "LOWER(b.bookTitle) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
            "LOWER(a.name) LIKE LOWER(CONCAT('%', :searchText, '%'))")
    List<Book> findBooksBySearchText(@Param("searchText") String searchText, Pageable pageable);

}
