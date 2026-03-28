package com.crud.repo;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface BookRepository extends ReactiveCrudRepository<Book, Integer> {

    //Customization
    @Query("select * from book_details where publisher = :name AND author = :auth")
    Flux<Book> getAllBooksByPublisherAndAuthor(String name, @Param("auth") String author);
}
