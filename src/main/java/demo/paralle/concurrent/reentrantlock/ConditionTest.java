package demo.paralle.concurrent.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    static class WaitTarget implements Runnable {
        @Override
        public void run() {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " get lock!");
            try {
                System.out.println(Thread.currentThread().getName() + " wait!");
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " continue!");
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
            System.out.println(Thread.currentThread().getName() + " finish!");
        }
    }

    static class SignalTarget implements Runnable {
        @Override
        public void run() {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " get lock!");
            condition.signal();
            System.out.println(Thread.currentThread().getName() + " signal!");
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " finish!");
        }
    }

    static class WaitTimeOutTarget implements Runnable {
        @Override
        public void run() {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " get lock!");
            try {
                System.out.println(Thread.currentThread().getName() + " wait!");
                condition.await(2, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " continue!");
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
            System.out.println(Thread.currentThread().getName() + " finish!");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new WaitTimeOutTarget());
        Thread t2 = new Thread(new SignalTarget());
        t1.start();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
