package demo.springdemo;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DemoCollect {
    public static void testAverage() {
        Double collect = Stream.of(1, 2, 3, 4, 5).collect(Collectors.averagingInt(a -> a));
        System.out.println("average:" + collect);
        Double d = Stream.of(1.0, 32.0, 21.1, 23.4, 21.0, 22.0, -2.0).collect(Collectors.averagingDouble(a ->
                a));
        System.out.println("double average:" + d);
        IntSummaryStatistics sum = Stream.of(12, 212312, 12, 212).collect(Collectors.summarizingInt(a -> a));
        System.out.println(sum);
    }

    public static void testPartion() {
        Map<Boolean, List<Integer>> result = Stream.of(1, 2, 3, 4, 5, 6).collect(Collectors.partitioningBy(a -> a % 2
                == 0));
        System.out.println(result);
    }

    static class Fruit {
        private String name;
        private double price;

        public Fruit(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }
    }

    public static void testGroup() {
        Fruit[] fruits = new Fruit[]{new Fruit("apple", 3), new Fruit("bana", 2), new Fruit("pair", 6), new
                Fruit("apple", 3), new Fruit
                ("bana", 2)};
        System.out.println("count by type");
        Map<String, Long> map = Arrays.stream(fruits).collect(Collectors.groupingBy(Fruit::getName, Collectors
                .counting()));
        Consumer<Map.Entry<String, Long>> output = a -> System.out.println("type:" + a.getKey() + " count:" + a
                .getValue());
        map.entrySet().forEach(output);
        System.out.println("sort by count desc");
        map.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(output);
        Map<String, Double> map2 = Arrays.stream(fruits).collect(Collectors.groupingBy(Fruit::getName, Collectors
                .averagingDouble(Fruit::getPrice)));
        System.out.println("sort by price");
        map2.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(a -> System.out.println("type:" + a
                .getKey() + " price:" + a.getValue()));
    }

    public static void testJoin() {
        String result = Stream.of("h", "e", "l", "l", "o").collect(Collectors.joining(",", "[", "]"));
        System.out.println(result);
    }

    public static void main(String[] args) {
//        testAverage();
//        testPartion();
//        testGroup();
        testJoin();
    }
}
