package demo.paralle.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolTest {
    static class TestTarget implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getId() + " done!");
        }
    }

    public static void main(String[] args) {
        Runnable target = new TestTarget();
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 20; i++) {
            service.submit(target);
        }

        service.shutdown();
    }
}
