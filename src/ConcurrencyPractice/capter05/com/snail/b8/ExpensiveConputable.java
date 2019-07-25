package ConcurrencyPractice.capter05.com.snail.b8;

import java.math.BigInteger;

public class ExpensiveConputable implements Computable<String,BigInteger> {
    @Override
    public BigInteger compute(String s) {
        //经过长时间计算
        return new BigInteger(s);
    }
}
