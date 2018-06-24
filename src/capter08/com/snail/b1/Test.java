package capter08.com.snail.b1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args){
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingDeque<>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,20,10000,TimeUnit.DAYS,blockingQueue);
        threadPoolExecutor.setRejectedExecutionHandler(threadPoolExecutor.getRejectedExecutionHandler());

        /**
         * 创建线程池，且使用调用者运行饱和策略
         */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10,20,1000,TimeUnit.MINUTES,new LinkedBlockingDeque<>());
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
