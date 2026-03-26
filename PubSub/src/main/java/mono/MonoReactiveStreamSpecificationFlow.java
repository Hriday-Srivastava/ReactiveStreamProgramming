package mono;

import org.reactivestreams.Subscription;

import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

public class MonoReactiveStreamSpecificationFlow {

	public static void main(String[] args) {
		
		// Correct way to create Mono
        Mono<String> monoPublisher = Mono.just("testing");
        
        // Subscribe with CoreSubscriber
        monoPublisher.subscribe(new CoreSubscriber<String>() {

            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("Subscription done");
                subscription.request(Long.MAX_VALUE); // Request items from publisher
            }

            @Override
            public void onNext(String data) {
                System.out.println("Data: " + data);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Error: " + throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("Completed");
            }
        });
        
        //In java8 Style same action or implementation of Mono Pub-Sub reactive programming specifications.
        System.out.println("***************Implementation Mono Using Java8 Lambda*************** ");
        monoPublisher.doOnSubscribe(sub -> System.out.println("Subscription done"))
        .subscribe(
            data -> System.out.println("Data: " + data),
            err -> System.out.println("Error: " + err.getMessage()),
            () -> System.out.println("Completed")
        );
        
        System.out.println("********************OR************************");
        monoPublisher.subscribe(
                data -> System.out.println("Data: " + data),              // onNext
                error -> System.out.println("Error: " + error.getMessage()), // onError
                () -> System.out.println("Completed")                    // onComplete
        );

	}

}
