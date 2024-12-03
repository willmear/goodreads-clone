package com.willmear.goodreads.domain.entity;

import com.willmear.goodreads.enums.BookStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Update {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Book book;
    private BookStatus status;
    @ManyToOne
    private User user;
    private String text;
    @OneToMany
    private List<Comment> commentList;


}
