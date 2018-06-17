package capter07.com.snail.b1;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

public class BrokenPrimeConsumerV2 extends Thread{
    final BlockingQueue<BigInteger> bigIntegers;
    final BrokenPrimeProducerV2 brokenPrimeProducer;
    public BrokenPrimeConsumerV2(BlockingQueue<BigInteger> bigIntegers, BrokenPrimeProducerV2 brokenPrimeProducer){
        this.bigIntegers = bigIntegers;
        this.brokenPrimeProducer = brokenPrimeProducer;
    }

    @Override
    public void run() {
        try{
            while (bigIntegers.size()<100){
                System.out.print(bigIntegers.take()+",");
                System.out.print(bigIntegers.size());
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
