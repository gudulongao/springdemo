package demo.springdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class ReduceDemo {
    private static BinaryOperator<Integer> binaryOperator = (count, item) -> {
        System.out.println("count:" + count + " item:" + item);
        count += item;
        return count;
    };

    public static void testReduceBinaryOperator() {
        Optional<Integer> optional = Stream.of(1, 2, 3, 4, 5).reduce(binaryOperator);
        System.out.println(optional.get());
    }

    public static void testReduceIdentity() {
        Object result = Stream.of(2).filter(i -> i < 0).reduce(null, binaryOperator);
        System.out.println(result);
        result = Stream.of(2).filter(i -> i < 0).reduce(-3, binaryOperator);
        System.out.println(result);
        result = Stream.of(1, 2, 3, 4, 5, 6).reduce(12, binaryOperator);
        System.out.println(result);
    }

    public static void testReduceCombiner() {
        BiFunction<List<Integer>, Integer, List<Integer>> biFunction = (list, item) -> {
            System.out.println("BiFunction...");
            System.out.println("list:" + list + " item:" + item);
            list.add(item);
            return list;
        };
        BinaryOperator<List<Integer>> binaryOperator = (a, b) -> {
            System.out.println("BinaryOperator...");
            System.out.println("1:" + a + " 2:" + b);
            a.addAll(b);
            return a;
        };
        List<Integer> list = Stream.of(1, 2, 3, 4, 5, 6).reduce(new ArrayList<>(), biFunction, binaryOperator);
        System.out.println(list);
    }

    public static void main(String[] args) {
//        testReduceBinaryOperator();
//        testReduceIdentity();
        testReduceCombiner();
    }
}
