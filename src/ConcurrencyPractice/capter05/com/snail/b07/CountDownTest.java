package ConcurrencyPractice.capter05.com.snail.b07;

import java.util.concurrent.CountDownLatch;

/**
 * 测试闭锁
 */
public class CountDownTest {


    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(100);
        for(int i=0; i<100; i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName());
            }).start();
            COUNT_DOWN_LATCH.countDown();
        }
        System.out.println("哈哈，还没开始，因为闭锁开没有计数到0");
        COUNT_DOWN_LATCH.await();
        System.out.println("哈哈，你们开始跑吧");
    }

}
