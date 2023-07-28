package com.example.functionalprogram;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author：江南
 * @Description 函数式编程
 * @since 1.0.0
 */
public class FPDemo {
    public static void main(String[] args) {

        Optional<Integer> max = Stream.of("foo", "bar", "hello")
                .map(s -> s.length())
                .filter(l-> l <= 3)
                .max((o1, o2) -> o1 - o2);
        System.out.println(max.get());//输出2


    }
}
