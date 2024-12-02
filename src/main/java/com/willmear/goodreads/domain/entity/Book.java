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
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String isbn;
    private String bookTitle;
    @ManyToOne
    private Author bookAuthor;
    private Integer yearOfPublication;
    private String publisher;
    private String imageUrlS;
    private String imageUrlM;
    private String imageUrlL;
}
