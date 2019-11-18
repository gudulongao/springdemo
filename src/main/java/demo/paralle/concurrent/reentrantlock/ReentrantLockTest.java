package demo.paralle.concurrent.reentrantlock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    private static ReentrantLock lock = new ReentrantLock();
    private static volatile int num = 0;

    static class CountTarget implements Runnable {
        @Override
        public void run() {
            lock.lock();
            num++;
            lock.unlock();
        }
    }

    public static void testCount() {
        ExecutorService service = new ThreadPoolExecutor(10, 20, 2000, TimeUnit.SECONDS, new ArrayBlockingQueue<>(20));
        Runnable target = new CountTarget();
        for (int i = 0; i < 1000; i++) {
            service.submit(target);
        }
        service.shutdown();
        System.out.println("result: " + num);
    }

    public static void main(String[] args) {
        testCount();

    }
}
