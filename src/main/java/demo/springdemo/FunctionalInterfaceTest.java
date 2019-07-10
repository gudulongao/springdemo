package demo.springdemo;

import java.util.Arrays;
import java.util.List;

@FunctionalInterface
public interface FunctionalInterfaceTest {
    public boolean isZero(int num);

    public static void main(String[] args) {
        FunctionalInterfaceTest test = n -> n == 0;
        List<Integer> list = Arrays.asList(1, 0, 3);
        list.forEach(i -> System.out.println(test.isZero(i)));
    }
}
