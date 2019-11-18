package demo.springdemo;

import java.util.function.BiFunction;
import java.util.function.Function;

public class DemoBiFunction {
    public static void main(String[] args) {
        BiFunction<Integer, Double, String> method1 = (a, b) -> {
            System.out.println("(int,double) -> string");
            String result = "result: " + (a + b);
            System.out.println("reult: " + result);
            return result;
        };
        method1.apply(1, 2.1d);

        Function<String, String> method2 = a -> {
            System.out.println("string -> string");
            String result = "hello: " + a;
            System.out.println(result);
            return result;
        };
        method1.andThen(method2).apply(1, 23.12d);
    }
}
