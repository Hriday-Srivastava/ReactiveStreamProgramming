package com.reactive.consumer.controller;

import com.reactive.consumer.dto.Book;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("consumer/library")
public class LibraryConsumerController {

    private final String baseUrl = "http://localhost:8080/library";
    private final WebClient webClient = WebClient.builder().build();

    @PostMapping
    public Mono<Book> saveBook(@RequestBody Book book){
        URI uri = UriComponentsBuilder.fromUriString(baseUrl).build().toUri();// Always create UriComponentsBuilder per request i.e in method only.
        return webClient.post()
                .uri(uri)
                .bodyValue(book)
                .retrieve()
                .bodyToMono(Book.class);
    }

    @GetMapping("/{bookId}")
    public Mono<Book> getBook(@PathVariable("bookId") int id){
        URI uri = UriComponentsBuilder  //Always create UriComponentsBuilder per request i.e in method only.
                                .fromUriString(baseUrl)
                                .path("/{bookId}")
                                .buildAndExpand(id)
                                .toUri();
        return webClient.get()
                        .uri(uri)
                        .retrieve()
                .bodyToMono(Book.class);

    }

    @GetMapping("/search")
    public Flux<Book> getAllBooks(String publisher, @RequestParam String author){

        URI uri = UriComponentsBuilder  //Always create UriComponentsBuilder per request i.e in method only.
                    .fromUriString(baseUrl)
                    .path("/search")
                    .queryParam("publisher", publisher)
                    .queryParam("author", author)
                    .build()
                    .toUri();

        return webClient.get()
                        .uri(uri)
                        .retrieve()
                        .bodyToFlux(Book.class);


    }



}
