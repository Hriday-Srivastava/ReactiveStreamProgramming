package mono;

import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

public class MonoImplementationWithMethods {

	public static void main(String[] args) {
		
		
		Mono<String> mono = Mono.just("Learn Mono Implementation With Some Methods");
		
		mono.subscribe(System.out::println);
		//Or
		mono.subscribe(data -> System.out.println(data));
		
		//log()
		Mono<String> monoLog = Mono.just("Learn Mono Implementation With log Methods").log();
		// 👉 Mono is not executed unless mono is subscribed.
		monoLog.subscribe();
		
		//error()
		/* 👉 ध्यान दो: onNext is not called❌ onComplete too not called ❌ Only onError called ✔*/
		Mono<String> monoError = Mono.error(new RuntimeException("Error Came"));

		monoError.subscribe(
		    data -> System.out.println(data),      // onNext
		    err -> System.out.println(err.getMessage()), // onError
		    () -> System.out.println("Completed")  // onComplete
		);
		System.out.println("********************** THEN ***********************");
		
		//then(): Is used to chaining the MONOs. Ignore previous mono result
		Mono<String> thenMono = Mono.just("We have learnt Then implementation in MONOs");
		Mono<String> instructionMono = Mono.just("We will learn implementation of Then").then(thenMono);
		instructionMono.subscribe(System.out::println);
		
		System.out.println("************** Print Both Statement using Then ***************");
		Mono<String> thenMono1 = Mono.just("We have learnt Then implementation in MONOs");
		Mono<String> instructionMono1 = Mono.just("We will learn implementation of Then")
											.doOnNext(System.out::println)
											.then(thenMono1);
		instructionMono1.subscribe(System.out::println);
		
		System.out.println("**************************zip Static Method*******************************");
		Mono<String> mono1 = Mono.just("Hello");
		Mono<String> mono2 = Mono.just("World");
		
		Mono<Tuple2<String, String>> combinedMono = Mono.zip(mono1, mono2);

		combinedMono
		    .subscribe(data -> {
		        System.out.println(data.getT1() + " " + data.getT2());
		    });
		
		System.out.println("**************************zipWith Instance Method*******************************");
		Mono<String> mono11 = Mono.just("Hello");
		Mono<String> mono12 = Mono.just("World");

		Mono<Tuple2<String, String>> combined = mono11.zipWith(mono12);

		combined.subscribe(tuple -> {
		    System.out.println(tuple.getT1()); // Hello
		    System.out.println(tuple.getT2()); // World
		});
		Mono<String> combinedZipWith = mono11.zipWith(mono12, (a, b) -> a + " " + b);
		combinedZipWith.subscribe(System.out::println); 
		
		System.out.println("***************Map Implementation***********");
		Mono<String> mono21 = Mono.just("india is the best");
		Mono<String> monoMap = mono21.map(data -> data.toUpperCase());
		monoMap.subscribe(System.out::println);
		
		//FlatMap:
		System.out.println("******************FlatMap***********************");
		Mono<String> flatMono = mono21.flatMap(data -> Mono.just(data.toUpperCase()));
		flatMono.subscribe(System.out::print);System.out.println();
		
		//onErrorResume
		System.out.println("************onErrorResume**************");
		Mono<Object> monoExceptionHandlling = Mono.error(new RuntimeException("Something went wrong"))
                .onErrorResume(err -> Mono.just("Recovered from error"));

		monoExceptionHandlling.subscribe(System.out::println);

		
		
		


		
		
		
		
		
		

	}

}
