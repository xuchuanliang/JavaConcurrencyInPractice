package capter10.com.snail.b1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LeftRightDeadlock {
    private static final Object left = new Object();
    private static final Object right = new Object();
    public void leftRight() throws InterruptedException {
        synchronized (left){
            synchronized (right){
                TimeUnit.SECONDS.sleep(10);
                for(int i=0;i<100;i++){
                    System.out.println("thread2"+i);
                }
                System.out.println("end1========================");
            }
        }
    }
    public void rightLeft() throws InterruptedException {
        synchronized (right){
            synchronized (left){
                TimeUnit.SECONDS.sleep(10);
                for(int i=0;i<100;i++){
                    System.out.println("thread1"+i);
                }
                System.out.println("end2========================");
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        LeftRightDeadlock leftRightDeadlock = new LeftRightDeadlock();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(()->{
            try {
                leftRightDeadlock.leftRight();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.submit(()->{
            try {
                leftRightDeadlock.rightLeft();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
