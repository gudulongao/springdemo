package demo.paralle.basic.thread.votailedemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountByVolatile {
    private static volatile int num;
    private static Lock lock = new ReentrantLock();

    static class MyThread extends Thread {
        @Override
        public void run() {
            lock.lock();
            System.out.println("update before num: " + num);
            num += 1;
            System.out.println("update after num: " + num);
            lock.unlock();
        }
    }

    public static void testCounts() {
        ExecutorService service = new ThreadPoolExecutor(100, 200, 2000, TimeUnit.SECONDS, new ArrayBlockingQueue<>
                (200));
        for (int i = 0; i < 1000; i++) {
            service.submit(new MyThread());
        }

        service.shutdown();
        System.out.println(num);
    }


    public static void main(String[] args) {
        testCounts();
    }
}
