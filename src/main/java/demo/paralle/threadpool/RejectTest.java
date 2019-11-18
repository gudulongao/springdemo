package demo.paralle.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RejectTest {

    public static void main(String[] args) {
        Runnable command = () -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getId() + " done!");
        };

        RejectedExecutionHandler reject = (r, e) -> {
            System.out.println("reject!");
        };
        ThreadPoolExecutor service = new ThreadPoolExecutor(5, 10, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5),
                reject);
        for (int i = 0; i < 30; i++) {
            service.submit(command);
        }

        service.shutdown();
    }

}
