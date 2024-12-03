package com.willmear.goodreads.service;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.willmear.goodreads.domain.entity.Book;
import com.willmear.goodreads.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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

    public ResponseEntity<List<Book>> searchBooks(String searchText) {

        Pageable firstFive = PageRequest.of(0,5);

        List<Book> books = bookRepository.findBooksBySearchText(searchText, firstFive);

        return ResponseEntity.ok(books);

    }

    public void upload() {

        try (CSVReader reader = new CSVReaderBuilder(new FileReader("C:\\Users\\willi\\Desktop\\goodreads-clone\\books_data\\books.csv"))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build()) // Specify custom delimiter
                .build()) {
            String[] line;
            List<Book> books = new ArrayList<>();
            boolean isHeader = true;

            int count=0;
            while ((line = reader.readNext()) != null) {
                if (isHeader) { // Skip the header line
                    isHeader = false;
                    continue;
                }

//                if (line.length < 2) {
//                    System.out.println("Skipping malformed row: " + String.join(",", line));
//                    continue;
//                }

                Book book = new Book();
                book.setIsbn(line[0]);
                book.setBookTitle(line[1]);
//                book.setBookAuthor(line[2]);
                books.add(book);
                count++;
                if (count==100) {
                    break;
                }
            }

            bookRepository.saveAll(books);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
