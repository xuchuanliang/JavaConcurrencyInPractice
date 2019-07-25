package ConcurrencyPractice.capter05.com.snail.b6;

import java.math.BigInteger;

/**
 * 实际计算成果
 */
public class ExpensiveFunction implements Computable<String,BigInteger>{

    /**
     * 经过长时间计算返回结果
     * @param arg
     * @return
     * @throws InterruptedException
     */
    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        return new BigInteger(arg);
    }
}
