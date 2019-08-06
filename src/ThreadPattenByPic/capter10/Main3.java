package ThreadPattenByPic.capter10;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author xuchuanliangbt
 * @title: Main3
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/516:28
 * @Version
 */
public class Main3 {

    public static void main(String[] args){
        test2();
    }

    /**
     * 计数器
     */
    public static void test1(){
        System.out.println("BEGIN");
        CountDownLatch countDownLatch = new CountDownLatch(10);
        ExecutorService executor = Executors.newFixedThreadPool(3);
        try {
            for(int i=0;i<10;i++){
                executor.execute(new Task(countDownLatch,i));
            }
            System.out.println("AWAIT");
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("END");
        }
    }

    /**
     * 循环栅栏
     */
    public static void test2(){
        System.out.println("BEGIN");
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3,()->System.out.println("Barrier Action!"));
        CountDownLatch countDownLatch = new CountDownLatch(3);
        try {
            for(int i=0;i<3;i++){
                executorService.execute(new MyTask(countDownLatch,cyclicBarrier,i));
            }
            System.out.println("AWAIT");
            countDownLatch.await();
        } catch (InterruptedException e) {
        } finally {
            executorService.shutdown();
            System.out.println("END");
        }
    }
}

/**
 * 计数器
 */
class Task implements Runnable{

    private final CountDownLatch countDownLatch;
    private final int context;
    private static final Random RANDOM = new Random(314159);
    public Task(CountDownLatch countDownLatch,int context){
        this.countDownLatch = countDownLatch;
        this.context = context;
    }

    @Override
    public void run() {
        doTask();
        countDownLatch.countDown();
    }

    private void doTask(){
        String name = Thread.currentThread().getName();
        System.out.println(name+":MyTask:Begin:coontext:"+context);
        try {
            Thread.sleep(RANDOM.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 循环栅栏
 */
class MyTask implements Runnable{

    private static final int PHASE = 5;
    private final CyclicBarrier cyclicBarrier;
    private final CountDownLatch countDownLatch;
    private final int context;
    private static final Random RANDOM = new Random();
    public MyTask(CountDownLatch countDownLatch,CyclicBarrier cyclicBarrier,int context){
        this.cyclicBarrier = cyclicBarrier;
        this.countDownLatch = countDownLatch;
        this.context = context;
    }

    @Override
    public void run() {
        try {
            for(int phase=0;phase<PHASE;phase++){
                doPhase(phase);
                cyclicBarrier.await();
            }
        } catch (InterruptedException e) {
        } catch (BrokenBarrierException e) {
        } finally {
            countDownLatch.countDown();
        }
    }

    private void doPhase(int phase){
        String name = Thread.currentThread().getName();
        System.out.println(name+":MyTask:BEGIN:context="+context+",phase="+phase);
        try{
            Thread.sleep(RANDOM.nextInt(3000));
        }catch (Exception e){

        }finally {
            System.out.println(name+":MyTask:END:context="+context+",phase="+phase);
        }
    }
}

