package capter07.com.snail.b1;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BrokenPrimeConsumer extends Thread{
    final BlockingQueue<BigInteger> bigIntegers;
    final BrokenPrimeProducer brokenPrimeProducer;
    public BrokenPrimeConsumer(BlockingQueue<BigInteger> bigIntegers,BrokenPrimeProducer brokenPrimeProducer){
        this.bigIntegers = bigIntegers;
        this.brokenPrimeProducer = brokenPrimeProducer;
    }

    @Override
    public void run() {
        try{
            while (bigIntegers.size()<10000){
                System.out.print(bigIntegers.take()+",");
                Thread.sleep(1000);
            }
        }catch (InterruptedException e){
            System.out.println("异常");
        }finally {
            System.out.println("取消操作");
            brokenPrimeProducer.cancelled();
        }
    }
}
