package demo.paralle.basic.thread;

public class WaitNotifyTest {
    private static final Object lock = new Object();

    static class ThreadA extends Thread {
        @Override
        public void run() {
            outThreadMsg("begin run...");
            synchronized (lock) {
                outThreadMsg("in lock...");
                try {
                    outThreadMsg("wait...");
                    lock.wait();
                    outThreadMsg("wait continue...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            outThreadMsg("out lock...");
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            outThreadMsg("begin run...");
            synchronized (lock) {
                outThreadMsg("in lock...");
                outThreadMsg("notify...");
                lock.notify();
                outThreadMsg("notify continue...");
            }
            outThreadMsg("out lock...");
        }
    }

    public static void outThreadMsg(String msg) {
        System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getId() + " " + msg);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new ThreadA();
        Thread threadB = new ThreadB();

        threadA.start();
        Thread.sleep(2000);
        threadB.start();

    }
}
