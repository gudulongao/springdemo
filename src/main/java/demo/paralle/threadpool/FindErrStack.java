package demo.paralle.threadpool;

import java.util.concurrent.*;

public class FindErrStack {
    static class Target implements Runnable {
        private int num;

        public Target(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            System.out.println("100/" + num + " result: " + (100 / num));
        }
    }

    static class ThreadPool extends ThreadPoolExecutor {
        public ThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                          BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override
        public Future<?> submit(Runnable task) {
            return super.submit(buildTaskProxy(task));
        }

        private Runnable buildTaskProxy(Runnable task) {
            return new Runnable() {
                @Override
                public void run() {
                    try {
                        task.run();
                    } catch (Exception e) {
                        System.out.println(Thread.currentThread().getName() + " err!");
                        e.printStackTrace();
                    }
                }
            };
        }
    }

    public static void main(String[] args) {
//        ThreadPoolExecutor service = new ThreadPoolExecutor(10, 20, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<>(20));
//        for (int i = 0; i < 10; i++) {
//            Future<?> submit = service.submit(new Target(i));
//            try {
//                submit.get();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        }
        ThreadPool service = new ThreadPool(0, 10, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<>(20));
        for (int i = 0; i < 10; i++) {
            service.submit(new Target(i));
        }
        service.shutdown();
    }
}
