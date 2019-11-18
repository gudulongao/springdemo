package demo.paralle.concurrent.reentrantlock;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    private static CountDownLatch latch = new CountDownLatch(5);

    static class SubTarget implements Runnable {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName() + " start!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName() + " done!");
            latch.countDown();
        }
    }

    static class MainTarget implements Runnable {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + " main target start!");
            SubTarget subTarget = new SubTarget();
            for (int i = 0; i < 5; i++) {
                new Thread(subTarget).start();
            }

            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + " main target done!");
        }
    }

    public static void main(String[] args) {
        MainTarget target = new MainTarget();
        new Thread(target).start();
    }
}
