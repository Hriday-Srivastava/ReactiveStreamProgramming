package flux;

import org.reactivestreams.Subscription;

import reactor.core.CoreSubscriber;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class FluxReactiveStreamSpecificationFlow {

	public static void main(String[] args) {

		
		// CoreSubscriber Implementation
		
        Flux<String> fluxPublisher = Flux.just("A", "B", "C");
        
        // Subscribe with CoreSubscriber
        fluxPublisher.subscribe(new CoreSubscriber<String>() {

            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("Subscription done");
                subscription.request(2l); // Request items from publisher
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
        System.out.println("***************Implementation Flux Using Java8 Lambda*************** ");
        fluxPublisher.doOnSubscribe(sub -> System.out.println("Subscription done"))
        .subscribe(
            data -> System.out.println("Data: " + data),
            err -> System.out.println("Error: " + err.getMessage()),
            () -> System.out.println("Completed")
        );
        
        System.out.println("********************OR************************");
        fluxPublisher.subscribe(
                data -> System.out.println("Data: " + data),              // onNext
                error -> System.out.println("Error: " + error.getMessage()), // onError
                () -> System.out.println("Completed")                    // onComplete
        );
        
     // पहले सिर्फ 2 items consume करेंगे
        fluxPublisher
            .take(2) // यह सिर्फ पहले 2 items देगा
            .subscribe(
                data -> System.out.println("Data: " + data),
                error -> System.out.println("Error: " + error.getMessage()),
                () -> System.out.println("Completed")
            );
        
        System.out.println("--- अब बाकी items ---");
        
        fluxPublisher
        .skip(2) // पहले 2 items skip कर दो
        .subscribe(
            data -> System.out.println("Data: " + data),
            error -> System.out.println("Error: " + error.getMessage()),
            () -> System.out.println("Completed")
        );
        
        //BaseSubscriber implementation
        fluxPublisher.subscribe(new BaseSubscriber<String>() {

            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                System.out.println("Subscription done");
                request(2); // पहली बार सिर्फ 2 items request कर रहे हैं
            }

            @Override
            protected void hookOnNext(String value) {
                System.out.println("Data: " + value);
                // आप यहाँ dynamic request कर सकते हैं
                // request(1); // हर item के बाद next request करने के लिए
            }

            @Override
            protected void hookOnComplete() {
                System.out.println("Completed");
            }

            @Override
            protected void hookOnError(Throwable throwable) {
                System.out.println("Error: " + throwable.getMessage());
            }
        });



	

	}

}
