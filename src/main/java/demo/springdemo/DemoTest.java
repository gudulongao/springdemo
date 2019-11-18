package demo.springdemo;

import java.util.*;
import java.util.stream.Stream;

public class DemoTest {
    interface A {
        default void hello() {
            System.out.println("hello A");
        }
    }

    interface B {
        default void hello() {
            System.out.println("hello B");
        }

        static void statcHello() {
            System.out.println("static hello");
        }
    }

    static class Cimpl implements B, A {
        @Override
        public void hello() {
            System.out.println("hello C");
        }
    }

    static class Dimpl implements B {
    }

    public static void testBitSet() {
        BitSet set1 = new BitSet();
        printSizeLength(set1);
        set1.set(64, true);
        printSizeLength(set1);
        set1.set(128, true);
        printSizeLength(set1);
        printByIndex(set1, 127);
        printByIndex(set1, 128);
        set1.clear();
        printSizeLength(set1);
        set1.clear(0, 256);
        printSizeLength(set1);

        BitSet set2 = new BitSet();
        printSizeLength(set2);
        int[] arr = new int[]{1, 24, 45, 42, 1, 7};
        for (int index : arr) {
            set2.set(index, true);
        }
        printSizeLength(set2);

        BitSet a = new BitSet();
        BitSet b = new BitSet();
        int[] arr1 = new int[]{1, 2, 3, 67, 50};
        int[] arr2 = new int[]{1, 2, 3, 59, 40};
        for (int index : arr1) {
            a.set(index, true);
        }
        for (int index : arr2) {
            b.set(index, true);
        }
        printSizeLength(a);
        printSizeLength(b);
        a.and(b);
        printSizeLength(a);
        printSizeLength(b);
        printContext(a);

        a.or(b);
        printContext(a);

        a.set(22);
        a.set(23);
        a.set(90);
//        a.andNot(b);
        printContext(a);
        a.xor(b);
        printContext(a);
    }

    public static void printSizeLength(BitSet set) {
        System.out.println("size: " + set.size() + " length: " + set.length() + " isEmpty: " + set.isEmpty());
    }

    public static void printByIndex(BitSet set, int index) {
        System.out.println("index: " + index + " flag: " + set.get(index));
    }

    public static void printContext(BitSet set) {
        if (set.isEmpty()) {
            System.out.println("[]");
            return;
        }
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < set.length(); i++) {
            if (set.get(i)) {
                builder.append(i);
                builder.append((i == (set.length() - 1)) ? "]" : ",");
            }
        }
        System.out.println(builder.toString());
    }

    public static void testConcat() {
        Stream.concat(Stream.of(1, 3, 6), Stream.of(7, 9, 12)).forEach(DemoTest::print);
        System.out.println();
        Stream.concat(Stream.of(1, 0, -1), Stream.of(7, 8, 2)).forEach(DemoTest::print);
        System.out.println();
        Stream.concat(Stream.of(1, 0, -1), Stream.of(7, 8, 2)).sorted().forEach(DemoTest::print);
    }

    public static void testDistinct() {
        Stream.of(1, 4, 10, 9, 8, 7, 10, 1, 5, 9).distinct().forEach(DemoTest::print);
    }

    public static void testFilter() {
        Stream.of(1, 89, 6, 7, 12, 5, 24, 69, 100, 4, 2, 0, 98, 22, 44).filter(item -> item % 2 == 0).forEach
                (DemoTest::print);
    }

    public static void testMap() {
        Stream.of(1, 6, 54, 23).filter(item -> item > 20).mapToDouble(item -> item).forEach
                (DemoTest::print);
        System.out.println();
        Stream.of("a", "b", "c").map(str -> str + "_" + str.toUpperCase()).forEach(DemoTest::print);
    }

    public static void testFlatMap() {
        List<String[]> list = new ArrayList<>();
        list.add(new String[]{"鸡蛋", "鸡蛋", "鸡蛋", "鸡蛋", "鸡蛋"});
        list.add(new String[]{"鸡蛋", "鸡蛋", "鸡蛋", "鸡蛋", "鸡蛋"});

        final int[] index = {0};
        list.stream().map(arr -> Arrays.stream(arr).map(item -> item.replace("鸡", "煎"))).forEach(arr -> {
                    System.out.println();
                    System.out.print("第" + (index[0]++) + "组: ");
                    arr.forEach(DemoTest::print);
                }
        );
        System.out.println();
        list.stream().flatMap(arr -> Arrays.stream(arr).map(item -> item.replace("鸡", "煎"))).forEach(DemoTest::print);
    }

    public static void testPeek() {
        Stream.of(1, 5, 6, 1, 4).peek(i -> System.out.println("peek deal:" + (i + 1))).forEach(System.out::println);
    }

    public static void testSkip() {
        Stream.of(1, 4, 2, 5, 6, 10, -1, 24, 55).skip(5).forEach(DemoTest::print);
        System.out.println();
        Stream<Integer> s = Stream.of(1, 4, 5, 2, 2, 21, 42).skip(12);
        System.out.println(s.count());
    }

    public static void testSorted() {
        Stream.of(1, 80, 2, 5, -100, 23232, 0, 9).sorted().forEach(DemoTest::print);
        System.out.println();
        DemoObj a = new DemoObj(120);
        DemoObj b = new DemoObj(4);
        DemoObj c = new DemoObj(-20);
        Stream.of(a, b, c).sorted(DemoObj::compareTo).forEach(i -> System.out.println(i.toString()));
    }

    static class DemoObj implements Comparable<DemoObj> {
        private int num;

        public DemoObj(int num) {
            this.num = num;
        }

        @Override
        public int compareTo(DemoObj o) {
            return this.num - o.num;
        }

        @Override
        public String toString() {
            return "{ num: " + this.num + "}";
        }
    }

    public static void testCount() {
        long count = Stream.of(1, 4, 2, 6, 10).count();
        System.out.println(count);
    }

    public static void testMax() {
        Optional<Integer> max = Stream.of(1, 5, 2, 7, 90, 200, 3, -200).max(Comparator.comparingInt(a -> a));
        System.out.println(max.get());
        DemoObj a = new DemoObj(120);
        DemoObj b = new DemoObj(4);
        DemoObj c = new DemoObj(-20);
        Optional<DemoObj> maxobj = Stream.of(a, b, c).max(Comparator.comparingInt(A -> A.num));
        System.out.println(maxobj.get());

    }

    public static void testMin() {
        Optional<Integer> min = Stream.of(0, 2, 6, -10, 12, 40, -2323).min(Comparator.comparingInt(a -> a));
        System.out.println(min.get());
        DemoObj a = new DemoObj(120);
        DemoObj b = new DemoObj(4);
        DemoObj c = new DemoObj(-20);
        Optional<DemoObj> minobj = Stream.of(a, b, c).min(Comparator.comparingInt(A -> A.num));
        System.out.println(minobj.get());
    }

    public static void testAllMatch() {
        boolean b = Stream.of(1, 2, 3, 4).allMatch(i -> i % 2 == 0);
        System.out.println(b);
        b = Stream.of(1, 2, 3, 4).allMatch(i -> i > 0);
        System.out.println(b);
    }

    public static void testAnyMatch() {
        boolean b = Stream.of(1, 2, 3, 4).anyMatch(i -> i % 2 == 0);
        System.out.println(b);
        b = Stream.of(1, 2, 3, 4).anyMatch(i -> i < 0);
        System.out.println(b);
    }

    public static void testNoneMatch() {
        boolean b = Stream.of(1, 2, 3, 4).noneMatch(i -> i % 5 == 0);
        System.out.println(b);
        b = Stream.of(2, 4, 6, 8).noneMatch(i -> i > 0);
        System.out.println(b);
    }

    public static void testFindAny() {
        Optional<Integer> any = Stream.of(1, 2, 3, 4).findAny();
        System.out.println(any.get());
    }

    public static void testFindFirst() {
        Optional<Integer> first = Stream.of(1, 2, 3, 4, 5, 6).findFirst();
        System.out.println(first.get());
        first = Stream.of(10, 2, 12, 3, 54, 12, 0, -1212).findFirst();
        System.out.println(first.get());
        first = Stream.of(10, 2, 12, 3, 54, 12, 0, -1212).findAny();
        System.out.println(first.get());
    }

    public static void testLimit() {
        Stream.of(1, 2, 3, 4).limit(5).forEach(DemoTest::print);
        System.out.println();
        Stream.of(1, 2, 3, 4, 5, 6).limit(3).forEach(DemoTest::print);
    }

    public static void print(Object obj) {
        System.out.print(obj + " ");
    }

    public static void main(String[] args) {
//        testBitSet();
//        testConcat();
//        testDistinct();
//        testFilter();
//        testMap();
//        testFlatMap();
//        testPeek();
//        testSkip();
//        testSorted();
//        testCount();
//        testMax();
//        testMin();
//        testAllMatch();
//        testAnyMatch();
//        testNoneMatch();
//        testFindAny();
//        testFindFirst();
//        testLimit();
    }
}
