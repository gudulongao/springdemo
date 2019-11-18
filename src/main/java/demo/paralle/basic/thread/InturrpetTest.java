package demo.paralle.basic.thread;

public class InturrpetTest {
    static class MyThread extends Thread {
        @Override
        public void run() {
            while (true) {
                if (isInterrupted()) {
                    System.out.println("inturreptd!");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    System.out.println(System.currentTimeMillis() + " doing...");
                } catch (InterruptedException e) {
                    System.out.println("catch interrupt excetion!");
                    interrupt();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread myThread = new MyThread();
        myThread.start();

        Thread.sleep(3000);

        myThread.interrupt();
    }
}
