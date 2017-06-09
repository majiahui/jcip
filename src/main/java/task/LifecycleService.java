package task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ExecutorService扩展于Executor，添加了一些用于生命周期管理的方法
 *
 * Created by bl05356 on 2017/6/9.
 */
public class LifecycleService {

    public final ExecutorService exec = Executors.newSingleThreadExecutor();

    public void startATask(String str) {
        if (!exec.isShutdown()) {
            Runnable task = new Runnable() {
                public void run() {
                    try {
                        System.out.println("begin: " + str + ", " + Thread.currentThread().getName());
                        Thread.sleep(1000);
                        System.out.println("end: " + str + ", " + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.submit(task);
        } else {
            System.out.println("do nothing " + str);
        }
    }

    public void stop() {
        exec.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        LifecycleService service = new LifecycleService();
        service.startATask("1");
        service.startATask("2");
        Thread.sleep(3000);
        service.stop();
        service.startATask("3");
    }
}
