package com.crud.controller;

import com.crud.repo.Book;
import com.crud.service.BookService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

    private BookService bookService;

    public LibraryController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Mono<Book> saveBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @GetMapping
    public Flux<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{bookId}")
    public Mono<Book> getBook(@PathVariable("bookId") int id){
        return bookService.findBookById(id);
    }

    @PutMapping
    public Mono<Book> updateBook(@RequestBody Book book){
        return bookService.updateBook(book);
    }

    @DeleteMapping("/{bookId}")
    public Mono<String> deleteBook(@PathVariable("bookId") int bookId){
        return bookService.deleteBook(bookId).then(Mono.just("Book is deleted successfullly "));
    }

    @PostMapping("/save")
    public Flux<Book> saveAllBooks(@RequestBody List<Book> books){
        return bookService.addAllBooks(books);
    }
}
