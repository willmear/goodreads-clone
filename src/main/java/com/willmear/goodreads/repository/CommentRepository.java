package com.willmear.goodreads.repository;

import com.willmear.goodreads.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
