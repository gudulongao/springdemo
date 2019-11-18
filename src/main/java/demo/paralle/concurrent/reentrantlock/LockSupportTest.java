package demo.paralle.concurrent.reentrantlock;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    static class TestTarget implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " start work!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " park");
            LockSupport.park();
            if (Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName() + " break!");
                return;
            }
            System.out.println(Thread.currentThread().getName() + " continue");
            System.out.println(Thread.currentThread().getName() + " finish work!");
        }
    }

    public static void main(String[] args) {
        Runnable target = new TestTarget();
        Thread t = new Thread(target);
        t.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " interrupt!");
        t.interrupt();
    }
}
