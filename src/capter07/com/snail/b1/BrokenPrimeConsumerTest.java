package capter07.com.snail.b1;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BrokenPrimeConsumerTest {
    public static void main(String[] args){
        BlockingQueue<BigInteger> bigIntegers = new ArrayBlockingQueue<>(10);
//        BrokenPrimeProducer brokenPrimeProducer = new BrokenPrimeProducer(bigIntegers);
        BrokenPrimeProducerV2 brokenPrimeProducer = new BrokenPrimeProducerV2(bigIntegers);
//        BrokenPrimeConsumer brokenPrimeConsumer = new BrokenPrimeConsumer(bigIntegers,brokenPrimeProducer);
        BrokenPrimeConsumerV2 brokenPrimeConsumer = new BrokenPrimeConsumerV2(bigIntegers,brokenPrimeProducer);
        brokenPrimeProducer.start();
        brokenPrimeConsumer.start();
    }
}
