package capter08.com.snail.b2;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

/**
 * 使用信号量控制任务提交的速率
 */
public class BoundedExecutor {
    /**
     * 线程执行器
     */
    private final Executor executor;
    /**
     * 信号量
     */
    private final Semaphore semaphore;

    public BoundedExecutor(Executor executor,int bound){
        this.executor = executor;
        this.semaphore = new Semaphore(bound);
    }

    /**
     * 执行任务方法，提交任务，执行任务前首先需要获取信号量的许可，执行完任务或抛出异常时，需要释放信号量
     * @param task
     * @throws InterruptedException
     */
    public void submitTask(final Runnable task) throws InterruptedException {
        //首先获取许可
        semaphore.acquire();
        try {
            executor.execute(()->{
                try {
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
        }catch (RejectedExecutionException e){
            semaphore.release();
        }

    }
}
