# CS122B Activity 6 - Reactor

- [Streams](#streams)
- [Mono](#mono)
- [WebClient](#webclient)


# Streams

Streams are a way we can take a `iterable` object (like `List`) and apply some "work" to it. 

When we create a Stream we can `map()` `filter()` `reduce()` and then finnaly `collect()` all the data.

### Map

Using the `map()` function we can state how we want to "transform" each element of the stream. In this example we use `map()` to "transform" each number of the stream in to its "cube" (num * 3)

```java 
List<Integer> nums = List.of(1, 2, 3, 4, 5);

nums = nums.stream()
           .map(num -> num * 3)
           .collect(Collectors.toList());

System.out.println(nums); // prints [3, 6, 9, 12, 15]
```

### Filter

Using the `filter()` function we can state "which" elements of the stream we want to keep. In this example we state that we only want the elemnts of the stream that the expression `num % 2 == 0` holds true.

```java 
List<Integer> nums = List.of(1, 2, 3, 4, 5);

nums = nums.stream()
           .filter(num -> num % 2 == 0)
           .collect(Collectors.toList());

System.out.println(nums); // prints [2, 4]
```

### Reduce

Using the `reduce()` function we can state how we want to "combine" all the elements of the stream. In this example we state that we want to combine all the elements by adding the elements with each other.

```java 
List<Integer> nums = List.of(1, 2, 3, 4, 5);

Optional<Integer> sum = nums.stream()
                            .reduce((num1, num2) -> num1 + num2);

System.out.println(sum.get()); // prints 15
```

### Example with all

```java 
List<Integer> nums = List.of(1, 2, 3, 4, 5);

Optional<Integer> sum = nums.stream()
                            .map(num -> num * 3)
                            .filter(num -> num % 2 == 0)
                            .reduce((num1, num2) -> num1 + num2);

System.out.println(sum.get()); // prints 18
```

# Mono

With streams in mind we can think of `Mono` as a "way to do a stream" that could potentially block but with a **single** element. So when you create a `Mono` it will not actually execute until you call the `.block() / .subscibe()` functions.

### Block Example

```java
Mono<Integer> numberMono =
    Mono.just(1)
        .map(num -> num * 3);

Integer number = numberMono.block();

System.out.println(number); // Prints 3
```

### Subscribe Example

```java
Mono<Integer> numberMono =
    Mono.just(1)
        .subscribeOn(PARALLEL_SCHEDULER) // This allows the mono to work on another thread
        .doOnNext(num -> simulatedLag()) // This is a "Step you can add" (Usefull for printing statements) 
                                         // This does not effect the value in the mono
        .map(num -> num * 3);

numberMono.subscribe(number -> System.out.println("This will print Second: " + number));

System.out.println("This will print first");
```

### Switching to another Mono

```java
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
```

# WebClient
