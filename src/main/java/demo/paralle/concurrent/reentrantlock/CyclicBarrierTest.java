package demo.paralle.concurrent.reentrantlock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    static class Soldier implements Runnable {
        private CyclicBarrier cyclicBarrier;

        public Soldier(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getId() + " start");
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getId() + " wait order");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getId() + " continue");

                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getId() + " wait order");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getId() + " done!");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    static class Commander implements Runnable {
        @Override
        public void run() {
            System.out.println("all soldier have a rest!");
        }
    }

    public static void main(String[] args) {
        Runnable commander = new Commander();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, commander);
        Runnable soldier = new Soldier(cyclicBarrier);

        for (int i = 0; i < 4; i++) {
            new Thread(soldier).start();
        }

    }
}
