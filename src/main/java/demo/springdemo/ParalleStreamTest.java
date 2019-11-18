package demo.springdemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ParalleStreamTest {
    private static List<Integer> list1 = new ArrayList<>();
    private static List<Integer> list2 = new ArrayList<>();
    private static List<Integer> list3 = new ArrayList<>();
    private static List<Integer> list4 = Collections.synchronizedList(new ArrayList<Integer>());
    private static List<Integer> list5 = Collections.synchronizedList(new LinkedList<>());
    private static Lock lock = new ReentrantLock();

    public static void test() {
        IntStream.range(0, 1000).forEach(i -> list1.add(i));
        IntStream.range(0, 1000).parallel().forEach(i -> list2.add(i));
        IntStream.range(0, 1000).parallel().forEach(i -> {
            try {
                lock.lock();
                list4.add(i);
            } finally {
                lock.unlock();
            }
        });
        System.out.println(list1.size());
        System.out.println(list2.size());
        System.out.println(list4.size());
    }

    private static void testSynchronziedQueue() {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(0, 20, 1000, TimeUnit.SECONDS, new ArrayBlockingQueue
                (100));
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executor.submit(() -> list4.add(finalI));
        }
        System.out.println(System.currentTimeMillis() + " shutdown start " + list4.size());
        executor.shutdown();
        System.out.println(System.currentTimeMillis() + " shutdown end " + list4.size());
        System.out.println(list4);
    }

    public static void main(String[] args) {
        test();
//        testSynchronziedQueue();
    }
}
