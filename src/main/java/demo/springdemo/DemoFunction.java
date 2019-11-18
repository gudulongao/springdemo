package demo.springdemo;

import java.util.function.Function;

public class DemoFunction {
    public static void main(String[] args) {
        //将整型转换成浮点
        Function<Integer, Double> method1 = a -> {
            System.out.println("int -> double");
            Double result = Double.valueOf(a);
            System.out.println("result: " + result);
            return result;
        };
        //将浮点转换成字符串
        Function<Double, String> method2 = a -> {
            System.out.println("double -> string");
            String result = "reuslt: " + a;
            System.out.println("result: " + result);
            return result;
        };
        //定义新的逻辑 整型转换成浮点 后再转换成字符串
        Function<Integer, String> method3 = method1.andThen(method2);
        //定义新逻辑 浮点转换成整型
        Function<Double, Integer> method4 = a -> {
            System.out.println("double -> int");
            int result = a.intValue();
            System.out.println("result: " + result);
            return result;
        };
        //定义新逻辑 浮点转换成整型 后整型转换成浮点 后再转换成字符串
        Function<Double, String> method5 = method3.compose(method4);
        System.out.println(method5.apply(2.23d));
        Function<Object, Object> identity = Function.identity();
        System.out.println(identity.apply("abc"));
    }
}
