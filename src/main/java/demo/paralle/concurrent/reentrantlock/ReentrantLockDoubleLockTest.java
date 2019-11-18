package demo.paralle.concurrent.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDoubleLockTest {
    private static volatile ReentrantLock lock = new ReentrantLock();

    static class TestTarget implements Runnable {
        @Override
        public void run() {
            long threadID = Thread.currentThread().getId();
            lock.lock();
            System.out.println(threadID + " get lock!");
            lock.lock();
            System.out.println(threadID + " lock double!");
            lock.unlock();
            System.out.println(threadID + " unlock 1th!");
        }
    }

    static class CorrectTarget implements Runnable {
        @Override
        public void run() {
            long threadID = Thread.currentThread().getId();
            lock.lock();
            System.out.println(threadID + " get lock!");
            lock.lock();
            System.out.println(threadID + " lock double!");
            lock.unlock();
            System.out.println(threadID + " unlock 1th!");
            lock.unlock();
            System.out.println(threadID + " unlock 2th!");
        }
    }

    static class WrongReleaseTarget implements Runnable {
        @Override
        public void run() {
            long threadID = Thread.currentThread().getId();
            lock.lock();
            System.out.println(threadID + " get lock!");
            lock.lock();
            System.out.println(threadID + " lock double!");
            lock.unlock();
            System.out.println(threadID + " unlock 1th!");
            lock.unlock();
            System.out.println(threadID + " unlock 2th!");
            lock.unlock();
            System.out.println(threadID + " unlock 3th!");
        }
    }

    public static void main(String[] args) {
        Runnable target = new WrongReleaseTarget();
        Thread t1 = new Thread(target);
        Thread t2 = new Thread(target);

        t1.start();
        t2.start();

    }
}
