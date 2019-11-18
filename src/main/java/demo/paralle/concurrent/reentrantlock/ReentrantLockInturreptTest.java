package demo.paralle.concurrent.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockInturreptTest {
    private static volatile ReentrantLock lockA = new ReentrantLock();
    private static volatile ReentrantLock lockB = new ReentrantLock();

    static class DeadLockThread extends Thread {
        protected boolean flag = true;

        public DeadLockThread(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void run() {
            boolean result = lock1th();
            if (!result) {
                finish(false);
                return;
            }
            doBusiness();
            result = lock2th();
            if (!result) {
                finish(false);
                return;
            }
            doBusiness();
            finish(true);
        }

        public void waitLock(String lockName) {
            System.out.println(Thread.currentThread().getName() + " wait lock" + lockName);
        }

        public boolean lock1th() {
            if (flag) {
                waitLock("A");
                lockA.lock();
            } else {
                waitLock("B");
                lockB.lock();
            }
            System.out.println(Thread.currentThread().getName() + " get lock" + (flag ? "A" : "B"));
            return true;
        }

        public boolean lock2th() {
            if (flag) {
                waitLock("B");
                lockB.lock();
            } else {
                waitLock("A");
                lockA.lock();
            }
            System.out.println(Thread.currentThread().getName() + " get lock" + (flag ? "B" : "A"));
            return true;
        }

        public void doBusiness() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void release() {
            if (lockA.isHeldByCurrentThread()) {
                lockA.unlock();
                System.out.println(Thread.currentThread().getName() + " release lockA");
            }
            if (lockB.isHeldByCurrentThread()) {
                lockB.unlock();
                System.out.println(Thread.currentThread().getName() + " release lockB");
            }
        }

        public void finish(boolean flag) {
            release();
            System.out.println(Thread.currentThread().getName() + " result :" + flag);
        }
    }

    static class DealDeadLockThread extends DeadLockThread {
        public DealDeadLockThread(boolean flag) {
            super(flag);
        }

        @Override
        public boolean lock1th() {
            boolean result = false;
            try {
                if (flag) {
                    waitLock("A");
                    lockA.lockInterruptibly();
                } else {
                    waitLock("B");
                    lockB.lockInterruptibly();
                }
                System.out.println(Thread.currentThread().getName() + " get lock" + (flag ? "A" : "B"));
                result = true;
            } catch (InterruptedException ignore) {
            }
            return result;
        }

        @Override
        public boolean lock2th() {
            boolean result = false;
            try {
                if (flag) {
                    waitLock("B");
                    lockB.lockInterruptibly();
                } else {
                    waitLock("A");
                    lockA.lock();
                }
                System.out.println(Thread.currentThread().getName() + " get lock" + (flag ? "B" : "A"));
                result = true;
            } catch (InterruptedException ignore) {
            }
            return result;
        }
    }

    static class DealDeadLockByTryLockThread extends DeadLockThread {
        private long timeout;
        private TimeUnit timeUnit;

        public DealDeadLockByTryLockThread(boolean flag, long timeout, TimeUnit timeUnit) {
            super(flag);
            this.timeout = timeout;
            this.timeUnit = timeUnit;
        }

        @Override
        public boolean lock1th() {
            boolean result = false;
            try {
                if (flag) {
                    waitLock("A");
                    result = lockA.tryLock(timeout, timeUnit);
                } else {
                    waitLock("B");
                    result = lockB.tryLock(timeout, timeUnit);
                }
                System.out.println(Thread.currentThread().getName() + " get lock" + (flag ? "A" : "B") + " reuslt: "
                        + result);
            } catch (InterruptedException ignore) {
            }
            return result;
        }

        @Override
        public boolean lock2th() {
            boolean result = false;
            try {
                if (flag) {
                    waitLock("B");
                    result = lockB.tryLock(timeout, timeUnit);
                } else {
                    waitLock("A");
                    result = lockA.tryLock(timeout, timeUnit);
                }
                System.out.println(Thread.currentThread().getName() + " get lock" + (flag ? "B" : "A") + " reuslt: "
                        + result);
            } catch (InterruptedException ignore) {
            }
            return result;
        }
    }

    public static void main(String[] args) {
        Thread t1 = new DealDeadLockByTryLockThread(true, 6, TimeUnit.SECONDS);
        Thread t2 = new DealDeadLockByTryLockThread(false, 9, TimeUnit.SECONDS);
        t1.start();
        t2.start();

//        try {
//
//            Thread.sleep(8000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(Thread.currentThread().getName() + " inturrept " + t1.getName());
//        t1.interrupt();
    }
}
