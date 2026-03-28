package com.crud.service;

import com.crud.repo.Book;
import com.crud.repo.BookRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Mono<Book> addBook(Book book) {
        return bookRepository.save(book).log();
    }

    @Override
    public Flux<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Mono<Book> findBookById(int id) {
        return bookRepository.findById(id);
    }

    @Override
    public Mono<Book> updateBook(Book book) {
        Mono<Book> oldBook = bookRepository.findById(book.getBookId());
        return oldBook.flatMap(book1 -> {
            book1.setName(book.getName());
            book1.setPublisher(book.getPublisher());
            book1.setAuthor(book.getAuthor());
            book1.setDescription(book.getDescription());
            return bookRepository.save(book1);
        });

    }

    @Override
    public Mono<Void> deleteBook(int bookId) {
        return bookRepository.findById(bookId).flatMap(book -> bookRepository.delete(book));
    }

    @Override
    public Flux<Book> addAllBooks(List<Book> books) {
        return bookRepository.saveAll(books);
    }

    @Override
    public Flux<Book> getAllBooksByPublisherAndAuthor(String publisherName, String authorName) {
        return bookRepository.getAllBooksByPublisherAndAuthor(publisherName, authorName);
    }
}
