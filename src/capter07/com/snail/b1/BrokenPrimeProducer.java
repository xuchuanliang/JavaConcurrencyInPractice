package capter07.com.snail.b1;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 不可靠的取消操作将把生产者置于阻塞的操作用
 */
public class BrokenPrimeProducer extends Thread{

    private final BlockingQueue<BigInteger> bigIntegers;
    private volatile boolean cancelled = false;

    public BrokenPrimeProducer(BlockingQueue<BigInteger> bigIntegers){
        this.bigIntegers = bigIntegers;
    }

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while(!cancelled){
            try {
                bigIntegers.put(p = p.nextProbablePrime());
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("中断");
            }
        }
    }

    public void cancelled(){
        this.cancelled = true;
    }
}
