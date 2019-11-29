package JavaCoreThreadPatten.capter02;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 线程终止和可见性：一个线程终止后该线程对共享变量的更新对于调用该线程的join方法的线程而言是可见的
 */
public class ThreadJoinVisibility {
    static int data = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(50));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //更新data的值
            data = 1;
        });
        thread.start();
        //等待线程thread结束后，main线程才开始继续运行
        thread.join();
        System.out.println(data);
    }
}
