package capter07.com.snail.b1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用volatile类型的域来保存取消状态
 */
public class PrimeGenerator implements Runnable{

    private final List<BigInteger> primes = new ArrayList<>();

    private volatile boolean caceled;


    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!caceled){
            p = p.nextProbablePrime();
            synchronized (this){
                primes.add(p);
            }
        }
    }

    public void canceled(){
        caceled = true;
    }

    public synchronized List<BigInteger> get(){
        return new ArrayList<>(primes);
    }
}
