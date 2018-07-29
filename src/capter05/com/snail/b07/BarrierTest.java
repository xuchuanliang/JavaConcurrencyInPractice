package capter05.com.snail.b07;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BarrierTest {

    public static void main(String[] args){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,()->{System.out.println("通过栅栏");});
        for(int i=0; i<10; i++){
            new Thread(()->{
                try {
                    System.out.println("到栅栏等一下");
                    int index = cyclicBarrier.await();
                    System.out.println(index);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        System.out.println("主线程");

    }
}
