package demo.springdemo.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class DemoForkJoin {
    static class CountTask<Integer> extends RecursiveTask {
        private static int LIMIT = 2;
        private int left;
        private int right;

        public CountTask(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        protected Object compute() {
            int result = 0;
            boolean computeAble = right - left <= LIMIT;
            if (computeAble) {
                for (int i = left; i <= right; i++) {
                    result += i;
                }
                System.out.println(Thread.currentThread().getId() + " computeAble: " + computeAble + " left: " + left
                        + " right: " + right + " " +
                        "reuslt:" + result);
            } else {
                int mid = (left + right) / 2;
                CountTask<Integer> lefitMid = new CountTask<>(left, mid);
                CountTask<Integer> rightMid = new CountTask<>(mid + 1, right);
                lefitMid.fork();
                rightMid.fork();
                int leftResult = (int) lefitMid.join();
                int rightResult = (int) rightMid.join();
                result = leftResult + rightResult;
                System.out.println(Thread.currentThread().getId() + " computeAble: " + computeAble + " left: " + left
                        + " right: " + right + " " +
                        "reuslt:" + result);
            }
            return result;
        }
    }

    public static void main(String[] args) throws Throwable {
        ForkJoinPool forkJoinPool = new ForkJoinPool(5);
        CountTask<Integer> task = new CountTask<>(1, 200);
        ForkJoinTask submit = forkJoinPool.submit(task);
        System.out.println(submit.get());

        if (task.isCompletedAbnormally()) {
            throw task.getException();
        }
    }
}
