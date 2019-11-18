package demo.paralle.concurrent.reentrantlock;

import java.util.concurrent.*;

public class SemaphoreTest {
    private static volatile Semaphore semaphore = new Semaphore(5);
    private static volatile Semaphore fairSemaphore = new Semaphore(5, true);

    static class ShareTarget implements Runnable {
        private Semaphore semaphore;

        public ShareTarget(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getId() + " " + System.currentTimeMillis() + " done!");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService service = new ThreadPoolExecutor(20, 20, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<>(20));
        Runnable target = new ShareTarget(fairSemaphore);
        for (int i = 0; i < 20; i++) {
            service.submit(target);
        }
        service.shutdown();
    }
}
