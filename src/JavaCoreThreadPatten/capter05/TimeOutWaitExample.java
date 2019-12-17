package JavaCoreThreadPatten.capter05;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TimeOutWaitExample {
    private static final Object lock = new Object();
    private static boolean ready = false;

    public static void main(String[] args) {
        Thread t = new Thread(()->{
            for(;;){
                synchronized (lock){
                    ready = new Random().nextInt(100)>50 ? true : false;
                    try {
                        TimeUnit.MILLISECONDS.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(ready){
                        lock.notify();
                    }
                    System.out.println(ready);
                }
            }
        });
        t.setDaemon(true);
        t.start();
        try {
            waiter(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waiter(long timeout) throws InterruptedException {
        if (timeout<0){
            throw new IllegalArgumentException();
        }
        long start = System.currentTimeMillis();
        long waitTime;
        long now;
        synchronized (lock){
            while (!ready){
                now = System.currentTimeMillis();
                //计算剩余等待时间
                waitTime = timeout-(now-start);
                if(waitTime<=0){
                    break;
                }
                //此处标识当前线程在lock这个对象的入口等待
                lock.wait(waitTime);
            }
            if(ready){
                //做正常的业务流程
                System.out.println("做正常的业务逻辑");
            }else {
                System.out.println("wait time out");
            }
        }
    }
}
