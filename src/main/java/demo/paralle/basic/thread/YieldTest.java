package demo.paralle.basic.thread;

public class YieldTest {
    static class MyThread extends Thread {
        @Override
        public void run() {
            outThreadMsg("begin run...");
            outThreadMsg("do work...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            outThreadMsg("yield...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            yield();
            outThreadMsg("go on...");
        }
    }

    public static void outThreadMsg(String msg) {
        System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getId() + " " + msg);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new MyThread().start();
        }
    }
}
