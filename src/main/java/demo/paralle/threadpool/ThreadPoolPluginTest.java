package demo.paralle.threadpool;

import java.util.concurrent.*;

public class ThreadPoolPluginTest {
    static class MyThreadPool extends ThreadPoolExecutor {

        public MyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, ThreadFactory
                threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, new ArrayBlockingQueue<>(20), threadFactory);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            System.out.println(t.getName() + " start run task");
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            System.out.println(Thread.currentThread().getName() + " finish task");
        }

        @Override
        protected void terminated() {
            System.out.println("ThreadPool will exits!");
        }
    }

    public static void main(String[] args) {
        Runnable command = () -> {
            System.out.println(Thread.currentThread().getName() + " done!");
        };
        ThreadFactory threadFactory = (r) -> {
            Thread thread = new Thread(r);
            return thread;
        };
        ThreadPoolExecutor service = new MyThreadPool(5, 10, 2, TimeUnit.SECONDS,
                threadFactory);
        for (int i = 0; i < 5; i++) {
            service.submit(command);
        }
        service.shutdown();

        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
