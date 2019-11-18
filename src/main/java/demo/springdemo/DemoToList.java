package demo.springdemo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DemoToList {
    public static void main(String[] args) {
        List<Integer> collect = Stream.of(1, 4, 123, 42, 53, 1212, 22, 0, 24, 212, 22, 53, 1231, 6).filter(i -> i % 2
                == 0).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }
}
