package com.crud.service;

import com.crud.repo.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface BookService {

    public Mono<Book> addBook(Book book);
    public Flux<Book> getAllBooks();
    public Mono<Book> findBookById(int id);
    public Mono<Book> updateBook(Book book);
    public Mono<Void> deleteBook(int id);
    public Flux<Book> addAllBooks(List<Book> books);
    public Flux<Book> getAllBooksByPublisherAndAuthor(String publisherName, String authorName);


}
