package demo.paralle.basic.thread.threadgroup;

public class ThreadGroupTest {
    private static volatile boolean flag = true;

    static class MyThread implements Runnable {
        @Override
        public void run() {
            Thread currentThread = Thread.currentThread();
            while (flag) {
                System.out.println(currentThread.getThreadGroup().getName() + "-" + currentThread.getId() + " " +
                        "runing...");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) {
        Runnable target = new MyThread();
        ThreadGroup group = new ThreadGroup("testThreadGroup");
        Thread a = new Thread(group, target, "A");
        Thread b = new Thread(group, target, "B");
        a.start();
        b.start();
        group.list();
        System.out.println(group.activeCount());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = false;
    }
}
