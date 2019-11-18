package demo.paralle.basic.thread;

public class JoinTest {
    static class MyThread extends Thread {
        @Override
        public void run() {
            outThreadMsg("begin run...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            outThreadMsg("end run");
        }
    }

    public static void outThreadMsg(String msg) {
        System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getId() + " " + msg);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread myThread = new MyThread();
        myThread.start();
        outThreadMsg("begin join...");
        myThread.join();
        outThreadMsg("go on");
    }
}
