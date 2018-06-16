package capter06.com.snail.b1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LifecycleWebService {
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);
    public static void main(String[] args){
        //Executor是否已经关闭
        EXECUTOR_SERVICE.isShutdown();
        //Executor是否终止
        EXECUTOR_SERVICE.isTerminated();
        //平缓关闭Executor
        EXECUTOR_SERVICE.shutdown();
        //立即粗暴终止Executor
        EXECUTOR_SERVICE.shutdownNow();
    }
}
