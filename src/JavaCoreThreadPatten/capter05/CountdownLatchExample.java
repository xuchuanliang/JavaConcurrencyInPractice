package JavaCoreThreadPatten.capter05;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 结果：
 * 一个线程执行完毕
 * 一个线程执行完毕
 * 一个线程执行完毕
 * 一个线程执行完毕
 * 一个线程执行完毕
 * 一个线程执行完毕
 * 一个线程执行完毕
 * 一个线程执行完毕
 * 一个线程执行完毕
 * 一个线程执行完毕
 * 十个任务已经执行完毕，开始执行下一阶段任务
 */
public class CountdownLatchExample {
    /**
     * 十个线程的任务执行完毕开始执行接下来的业务
     */
    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(10);

    public static void main(String[] args){
        try {
            for(int i=0;i<10;i++){
                new Thread(new Handler()).start();
                TimeUnit.SECONDS.sleep(1);
            }
            COUNT_DOWN_LATCH.await();
            System.out.println("十个任务已经执行完毕，开始执行下一阶段任务");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Handler implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
                System.out.println("一个线程执行完毕");
            } catch (InterruptedException e) {
            }finally {
                //注意，此处为了保证计数器能够释放，应当放在finally中释放
                COUNT_DOWN_LATCH.countDown();
            }
        }
    }
}
