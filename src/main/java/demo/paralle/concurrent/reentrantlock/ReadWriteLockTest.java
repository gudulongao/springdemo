package demo.paralle.concurrent.reentrantlock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
    private static int num = 0;

    static class ReadTarget implements Runnable {
        @Override
        public void run() {
            readLock.lock();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getId() + " : " + System.currentTimeMillis() + " read vaule: "
                    + num);
            readLock.unlock();
        }
    }

    static class WriteTarget implements Runnable {
        @Override
        public void run() {
            writeLock.lock();
            num++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getId() + " : " + System.currentTimeMillis() + " write opr");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        WriteTarget writeTarget = new WriteTarget();
        ReadTarget readTarget = new ReadTarget();
        for (int i = 0; i < 10; i++) {
            if (i % 5 == 0) {
                new Thread(writeTarget).start();
            }
            new Thread(readTarget).start();

        }
    }
}
