package ConcurrencyPractice.capter08.com.snail.b1;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

/**
 * 使用Semaphore（信号量）来控制任务的提交速率
 * 设置信号量的上界设置为线程池的大小加上可排队任务的数量，这是因为信号量需要控制正在执行的和等待执行的任务数量
 */
public class BoundedExecutor {
    private final Executor exec;
    private final Semaphore semaphore;
    public BoundedExecutor(Executor executor,int bound){
        this.exec = executor;
        this.semaphore = new Semaphore(bound);
    }
    public void submitTask(final Runnable command) throws InterruptedException {
        semaphore.acquire();
        try{
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    try{
                        command.run();
                    }finally {
                        semaphore.release();
                    }
                }
            });
        }catch (RejectedExecutionException e){
            semaphore.release();
        }

    }
}
