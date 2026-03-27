package flux;

import java.time.Duration;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxImplementationWithMethods {

	public static void main(String[] args) {
		
		//concat
		System.out.println("********************concat Static Method***********************");
		Flux<String> flux1 = Flux.just("A", "B", "C");
		Flux<String> flux2 = Flux.just("D", "E", "F");

		Flux<String> result = Flux.concat(flux1, flux2);
		result.subscribe(System.out::println);
		//concatWith
		System.out.println("********************concatWith Instance Method***********************");
		Flux<String> flux3 = Flux.just("G", "H", "I");
		Flux<String> flux4 = Flux.just("J", "K", "L");
		Flux<String> result1 = flux3.concatWith(flux4);
		result1.subscribe(System.out::println);
		
		//thenMany
		System.out.println("********************thenMany Method***********************");
		Mono<String> mono = Mono.just("Hello");
		Flux<String> flux5 = Flux.just("M", "N", "O");
		Flux<String> result3 = mono.thenMany(flux5);
		result3.subscribe(System.out::println);
		
		//flatMapMany()
		System.out.println("********************flatMany Method***********************");
		Mono<String> mono1 = Mono.just("Java");

		Flux<String> flux6 = mono1.flatMapMany(data -> 
		    Flux.just(data + " 8", data + " 11", data + " 17")
		);
		flux6.subscribe(System.out::println);
		
		//2nd Example
		Mono<String> userMono = Mono.just("User1");
		Flux<String> orders = userMono.flatMapMany(user -> 
		    Flux.just("Order1 of " + user, "Order2 of " + user)
		);
		orders.subscribe(System.out::print);System.out.println();
		
		System.out.println("********************merge***********************");
		Flux<String> flux7 = Flux.just("Q", "R", "S");
		Flux<String> flux8 = Flux.just("T", "U", "V");

		Flux<String> result4 = Flux.merge(flux7, flux8);
		result4.subscribe(System.out::println);
		
		Flux<String> flux9 = Flux.just("A", "B")
		        .delayElements(Duration.ofSeconds(1));
		Flux<String> flux10 = Flux.just("X", "Y");
		Flux<String> result5 = Flux.merge(flux9, flux10);
		result5.subscribe(System.out::println);
		
		//cache
		System.out.println("********************cache***********************");
		Flux<String> flux = Flux.just("X", "Y", "Z")
                .doOnSubscribe(s -> System.out.println("Subscribed"));

		Flux<String> cachedFlux = flux.cache();
 
		System.out.println("First Subscriber:");
		cachedFlux.subscribe(System.out::println);

		System.out.println("Second Subscriber:");
		cachedFlux.subscribe(System.out::println);
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
