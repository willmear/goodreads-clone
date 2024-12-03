package com.willmear.goodreads.repository;

import com.willmear.goodreads.domain.entity.Update;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpdateRepository extends JpaRepository<Update, Long> {
}
