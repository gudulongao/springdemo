package demo.paralle.concurrent.reentrantlock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class FairLockTest {
    private static volatile ReentrantLock fairLock = new ReentrantLock(true);
    private static volatile boolean isRuning = true;

    static class FairTarget implements Runnable {
        @Override
        public void run() {
            while (isRuning) {
                fairLock.lock();
                System.out.println(Thread.currentThread().getName() + " get lock!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (fairLock.isHeldByCurrentThread()) {
                    fairLock.unlock();
                    System.out.println(Thread.currentThread().getName() + " release lock!");
                }
            }
            System.out.println(Thread.currentThread().getName() + " stop!");
        }
    }

    public static void main(String[] args) {
        Runnable target = new FairTarget();
        ExecutorService service = new ThreadPoolExecutor(10, 20, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<>(50));

        for (int i = 0; i < 5; i++) {
            service.submit(target);
        }

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isRuning = false;
        service.shutdown();
    }

}
