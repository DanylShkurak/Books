package org.example.books.controller;

import org.example.books.entity.Book;
import org.example.books.entity.dto.BookDTO;
import org.example.books.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;
    private final ModelMapper modelMapper;

    public BookController(BookService bookService, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/scrape")
    public ResponseEntity<List<BookDTO>> scrapeAndSave(@RequestParam(defaultValue = "1") int page) {
        List<Book> books = bookService.scrapeAndSaveBooks(page);
        List<BookDTO> bookDTOS = books.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .toList();
        return ResponseEntity.ok(bookDTOS);
    }
}
