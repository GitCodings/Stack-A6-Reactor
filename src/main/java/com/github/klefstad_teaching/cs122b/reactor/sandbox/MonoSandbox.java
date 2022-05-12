package com.github.klefstad_teaching.cs122b.reactor.sandbox;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class MonoSandbox
{
    private final static Scheduler PARALLEL_SCHEDULER = Schedulers.parallel();

    public static void run()
    {
        blockExample();
        subscribeExample();
        switchingToAnotherMono();
        creatingACallableMono();
    }

    private static void blockExample()
    {
        Mono<Integer> numberMono =
            Mono.just(1)
                .map(num -> num * 3);

        Integer number = numberMono.block();

        System.out.println(number); // Prints 3
    }

    private static void subscribeExample()
    {
        Mono<Integer> numberMono =
            Mono.just(1)
                .subscribeOn(PARALLEL_SCHEDULER) // This allows the mono to work on another thread
                .doOnNext(num -> simulatedLag()) // This is a "Step you can add" (Usefull for printing statements)
                                                 // This does not effect the value in the mono
                .map(num -> num * 3);

        numberMono.subscribe(number -> System.out.println("This will print Second: " + number));

        System.out.println("This will print first");
    }

    private static void switchingToAnotherMono()
    {
        Mono<String> evenString = Mono.just("This is a even number");
        Mono<String> oddString  = Mono.just("This is a odd number");

        Mono<String> switchedMono =
            Mono.just(1)
                // You can not map to another mono, if you want to go to another mono in one
                // of these steps you must use "flatMap" rather than "map"
                .flatMap(num -> num % 2 == 0 ? evenString : oddString)
                .doOnNext(value ->
                             // We can see we now switched from an Integer to a String mono
                              System.out.println(value.getClass())
                );

        String value = switchedMono.block();
        System.out.println(value);
    }


    private static void creatingACallableMono()
    {
        // This is dangerous, we can not use Mono::just for
        // work that will "block". The purpose is to leave
        // that work for when the Mono is called (.subscribe() or .block())
        Mono<Integer> wrongWayToDoThis = Mono.just(getAValueAfterSomeLag());

        // This "Wraps" our function in a lambda. When you call
        // this Mono (.subscribe() or .block()) then it will call
        // that function
        Mono<Integer> correctWay = Mono.fromCallable(() -> getAValueAfterSomeLag());
    }

    private static Integer getAValueAfterSomeLag()
    {
        System.out.println("Starting with work!");
        simulatedLag();
        System.out.println("Done with work!");
        return 1;
    }
    private static void simulatedLag()
    {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
