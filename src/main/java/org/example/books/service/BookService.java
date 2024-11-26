package org.example.books.service;

import org.example.books.entity.Book;
import org.example.books.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ScrapeBook scrapeBook;

    public BookService(BookRepository bookRepository, ScrapeBook scrapeBook) {
        this.bookRepository = bookRepository;
        this.scrapeBook = scrapeBook;
    }

    public List<Book> scrapeAndSaveBooks(int pageNumber) {
        List<Book> books = scrapeBook.scrapeBooks(pageNumber);
        return bookRepository.saveAll(books);
    }
}
