package com.willmear.goodreads.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private User user;
    private String text;
}
