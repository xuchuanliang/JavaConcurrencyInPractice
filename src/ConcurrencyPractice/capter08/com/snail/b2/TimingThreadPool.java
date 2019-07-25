package ConcurrencyPractice.capter08.com.snail.b2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

/**
 * 扩展线程池：增加了日志和计时等功能的线程池
 */
public class TimingThreadPool extends ThreadPoolExecutor {

    /**
     * ThreadLocal，记录每一个线程的开始时间（beforeExecutor时使用）
     */
    private final ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 日志记录
     */
    private final Logger logger = Logger.getLogger(TimingThreadPool.class.getName());

    /**
     * 线程安全的AtomicLong，记录执行的任务数量(afterExecutor时使用)
     */
    private final AtomicLong numTasks = new AtomicLong();

    /**
     * 线程安全的AtomicLong，记录任务执行的总时间（terminated时使用）
     */
    private final AtomicLong totalTime = new AtomicLong();

    /**
     * 某个线程执行任务之前
     * @param t
     * @param r
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        logger.fine(String.format("Thread %s：start %s",t,r));
        startTime.set(System.nanoTime());
    }

    /**
     * 某个线程任务执行之后
     * @param r
     * @param t
     */
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        try {
            long endTime = System.nanoTime();
            long taskTime = endTime-startTime.get();
            numTasks.incrementAndGet();
            totalTime.addAndGet(taskTime);
            logger.fine(String.format("Thread %s：end %s，time=%dns",t,r,taskTime));
        } finally {
            super.afterExecute(r, t);
        }
    }

    /**
     * 线程池停止时，计算任务的平均耗时
     */
    @Override
    protected void terminated() {
        try {
            logger.info(String.format("Terminated：avg time=%dns",totalTime.get()/numTasks.get()));
        } finally {
            super.terminated();
        }
    }

    public TimingThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }
}
