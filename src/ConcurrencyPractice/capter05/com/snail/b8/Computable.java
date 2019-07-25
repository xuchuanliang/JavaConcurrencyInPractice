package ConcurrencyPractice.capter05.com.snail.b8;

import java.util.concurrent.ExecutionException;

/**
 * 计算类接口
 * @param <A> 参数
 * @param <V> 计算结果
 */
public interface Computable<A,V> {
    /**
     * 计算
     * @param a
     * @return
     */
    V compute(A a) throws ExecutionException, InterruptedException;
}
