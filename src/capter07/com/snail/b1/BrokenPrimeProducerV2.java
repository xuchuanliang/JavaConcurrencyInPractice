package capter07.com.snail.b1;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * 通过中断来取消操作
 */
public class BrokenPrimeProducerV2 extends Thread{

    private final BlockingQueue<BigInteger> bigIntegers;
    private volatile boolean cancelled = false;

    public BrokenPrimeProducerV2(BlockingQueue<BigInteger> bigIntegers){
        this.bigIntegers = bigIntegers;
    }

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        try {
            while(!Thread.currentThread().isInterrupted()){
                bigIntegers.put(p = p.nextProbablePrime());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("中断");
        }
    }

    public void cancelled(){
        this.interrupt();
    }
}
