package demo.springdemo;

import java.util.Comparator;
import java.util.function.BinaryOperator;

public class DemoBinaryOperator {
    static class DemoBean {
        private int age;
        private String name;

        public DemoBean(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "DemoBean{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        //定义参数bean
        DemoBean p1 = new DemoBean(1, "12");
        DemoBean p2 = new DemoBean(4, "44");
        //定义比较器
        Comparator<DemoBean> comparator = Comparator.comparingInt(a -> a.age);
        BinaryOperator<DemoBean> operator = BinaryOperator.maxBy(comparator);
        DemoBean result = operator.apply(p1, p2);
        System.out.println(result);
        operator = BinaryOperator.minBy(comparator);
        result = operator.apply(p1, p2);
        System.out.println(result);
    }
}
