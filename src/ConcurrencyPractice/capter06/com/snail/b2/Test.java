package ConcurrencyPractice.capter06.com.snail.b2;

import java.util.concurrent.*;

public class Test {
   static ExecutorService executorService = Executors.newFixedThreadPool(10);
   static BlockingQueue blockingQueue = new ArrayBlockingQueue(200);
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
                executorService.submit(()->{
                    while (true){
                        System.out.println("11");
                    }
                });
        }).start();
        blockingQueue.take();
        System.out.println("--------------");
        ExecutorService executorService = Executors.newCachedThreadPool();
        //如果线程池终止，则应该关闭线程池
        if(executorService.isTerminated()){
            executorService.shutdown();
        }

        //固定大小线程池
        ExecutorService executorService1 = Executors.newFixedThreadPool(10);
        //可缓存的线程池，线程会根据并发情况动态增加或删除
        ExecutorService executorService2 = Executors.newCachedThreadPool();
        //串行执行的线程池
        ExecutorService executorService3 = Executors.newSingleThreadExecutor();
        //固定长度线程池，可以定时或周期执行
        ExecutorService executorService4 = Executors.newScheduledThreadPool(10);
        //完成服务，使用队列实现的多组任务结果获取
        CompletionService<String> completionService = new ExecutorCompletionService<>(executorService);
    }
}
