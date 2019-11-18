package demo.paralle.threadpool;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledTest {
    static class TestTarget implements Runnable {
        @Override
        public void run() {
            System.out.println(LocalDateTime.now() + " " + Thread.currentThread().getId() + " begin!");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(LocalDateTime.now() + " " + Thread.currentThread().getId() + " done!");
        }
    }

    public static void testBySchedule() {
        System.out.println(LocalDateTime.now() + " start work...");
        Runnable target = new TestTarget();
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
//        service.schedule(target, 6, TimeUnit.SECONDS);
//        service.shutdown();
//        service.scheduleAtFixedRate(target, 0, 2, TimeUnit.SECONDS);
        service.scheduleWithFixedDelay(target, 0, 2, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        testBySchedule();
    }
}
