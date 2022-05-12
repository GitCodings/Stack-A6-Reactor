package com.github.klefstad_teaching.cs122b.route.sandbox;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamsSandbox
{
    public static void run()
    {
        mapExample();
        filterExample();
        reduceExample();
        multipleExample();
    }

    private static void mapExample()
    {
        List<Integer> nums = List.of(1, 2, 3, 4, 5);

        nums = nums.stream()
                   .map(num -> num * 3)
                   .collect(Collectors.toList());

        System.out.println(nums); // prints [3, 6, 9, 12, 15]
    }

    private static void filterExample()
    {
        List<Integer> nums = List.of(1, 2, 3, 4, 5);

        nums = nums.stream()
                   .filter(num -> num % 2 == 0)
                   .collect(Collectors.toList());

        System.out.println(nums); // prints [2, 4]
    }

    private static void reduceExample()
    {
        List<Integer> nums = List.of(1, 2, 3, 4, 5);

        Optional<Integer> sum = nums.stream()
                                    .reduce((num1, num2) -> num1 + num2);

        System.out.println(sum.get()); // prints 15
    }

    private static void multipleExample()
    {
        List<Integer> nums = List.of(1, 2, 3, 4, 5);

        Optional<Integer> sum = nums.stream()
                                    .map(num -> num * 3)
                                    .filter(num -> num % 2 == 0)
                                    .reduce((num1, num2) -> num1 + num2);

        System.out.println(sum.get()); // prints 18
    }
}
