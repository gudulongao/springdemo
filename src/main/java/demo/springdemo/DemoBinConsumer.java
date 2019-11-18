package demo.springdemo;

import java.util.function.BiConsumer;

public class DemoBinConsumer {
    public static void main(String[] args) {
        //定义加法逻辑
        BiConsumer<Integer, Integer> method1 = (a, b) -> {
            System.out.println(a + " + " + b + " = " + (a + b));
        };
        //定义减法逻辑
        BiConsumer<Integer, Integer> method2 = (a, b) -> {
            System.out.println(a + " - " + b + " = " + (a - b));
        };
        //定义新的逻辑 先执行加法再执行减法
        BiConsumer<Integer, Integer> method3 = method1.andThen(method2);
        //定义除以0的逻辑
        BiConsumer<Integer, Integer> mehthod4 = (a, b) -> {
            System.out.println(a + " / 0 = " + (a / 0));
        };
        //定义新的逻辑 先执行加法再执行减法 再执行除0 再执行加法再执行减法
        BiConsumer<Integer, Integer> method5 = method3.andThen(mehthod4).andThen(method3);
        method5.accept(10, 20);
    }
}
