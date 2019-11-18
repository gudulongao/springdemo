package demo.paralle.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadFactoryTest {
    public static void main(String[] args) {
        Runnable command = () -> {
            System.out.println(Thread.currentThread().getName() + " done!");
        };
        ThreadFactory threadFactory = (r) -> {
            Thread thread = new Thread(r);
            thread.setName("test thread");
            return thread;
        };

        ThreadPoolExecutor service = new ThreadPoolExecutor(5, 10, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<>(20)
                , threadFactory);
        for (int i = 0; i < 20; i++) {
            service.submit(command);
        }

        service.shutdown();
    }
}
