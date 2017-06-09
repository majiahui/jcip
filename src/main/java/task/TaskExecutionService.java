package task;

import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Executor基于生产者-消费者模式，将任务的提交和执行过程解耦开来
 * 使用Executor代替 new Thread(runnable).startATask()
 *
 * 通过调用Executors静态工厂方法创建线程池
 *
 * Created by bl05356 on 2017/6/9.
 */
public class TaskExecutionService {

    public static final int NUM_OF_THREADS = 1;

    public static final Executor exec = Executors.newFixedThreadPool(NUM_OF_THREADS);

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            final String input = scanner.next();
            Runnable task = new Runnable() {
                public void run() {
                    try {
                        handleRequest(input);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.execute(task);
        }
    }

    public static void handleRequest(String input) throws InterruptedException {
        System.out.println("begin: " + input + ", " + Thread.currentThread().getName());
        Thread.sleep(3000);
        System.out.println("end: " + input + ", " + Thread.currentThread().getName());
    }
}
