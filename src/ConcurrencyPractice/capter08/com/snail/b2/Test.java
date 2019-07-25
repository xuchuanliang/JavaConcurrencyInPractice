package ConcurrencyPractice.capter08.com.snail.b2;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args){
        System.out.println( Runtime.getRuntime().availableProcessors());
    }

    /**
     * 创建带有饱和策略的线程池
     * new ThreadPoolExecutor.CallerRunsPolicy() 调用者运行饱和策略
     * new ThreadPoolExecutor.AbortPolicy() 中止饱和策略（默认饱和策略）
     * new ThreadPoolExecutor.DiscardPolicy() 抛弃饱和策略
     * new ThreadPoolExecutor.DiscardOldestPolicy() 抛弃最旧的饱和策略
     */
    public static void test1(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,8,0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>(10),new ThreadPoolExecutor.CallerRunsPolicy());
    }


}
