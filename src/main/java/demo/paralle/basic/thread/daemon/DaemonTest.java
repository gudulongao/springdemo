package demo.paralle.basic.thread.daemon;

public class DaemonTest {
    static class MyThread extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("I am alive!");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread daemon = new MyThread();
        daemon.setDaemon(true);
        daemon.start();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" main exit!");
    }
}
