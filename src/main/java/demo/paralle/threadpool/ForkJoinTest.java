package demo.paralle.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest {
    public static final int LIMIT = 2;

    static class Target extends RecursiveTask {
        private int lefitNum;
        private int rightNum;

        public Target(int lefitNum, int rightNum) {
            this.lefitNum = lefitNum;
            this.rightNum = rightNum;
        }

        @Override
        protected Object compute() {
            //求差
            int sub = rightNum - lefitNum;
            //与界限做比对，判断是否需要再次划分任务
            boolean flag = sub > LIMIT;
            if (flag) {
                //求中数
                int mid = (lefitNum + rightNum) / 2;
                //构建左半任务
                Target leftTarget = new Target(lefitNum, mid);
                //构建右半任务
                Target rightTarget = new Target(mid + 1, rightNum);
                //执行子任务
                leftTarget.fork();
                //执行右任务
                rightTarget.fork();
                //获取左半边任务结果
                int leftResult = (int) leftTarget.join();
                //获取右半边任务结果
                int rightResult = (int) rightTarget.join();
                //合并结果
                return leftResult + rightResult;
            }
            //符合界限时，依次求和
            int result = 0;
            for (int i = lefitNum; i <= rightNum; i++) {
                result += i;
            }
            return result;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        RecursiveTask task = new Target(1, 6);
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask submit = pool.submit(task);
        System.out.println(submit.get());
    }
}
