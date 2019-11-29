package JavaCoreThreadPatten.capter03;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExplicitLockInfo {
    private static final Lock LOCK = new ReentrantLock();
    private static int sharedData = 0;
    public static void main(String[] args){
        Thread t = new Thread(()->{
            LOCK.lock();
            try{
                try {
                    Thread.sleep(220000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sharedData++;
            }finally {
                LOCK.unlock();
            }
        });
        t.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOCK.lock();
        try {
            System.out.println("sharedData :"+sharedData);
        }finally {
            LOCK.unlock();
        }
    }
}
