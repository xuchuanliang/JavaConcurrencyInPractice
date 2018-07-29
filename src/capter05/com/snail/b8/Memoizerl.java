package capter05.com.snail.b8;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 缓存第一版,包装真实计算
 * 使用synchronized对整个计算过程加锁，虽然安全，但会导致其他线程长时间等待，效率非常差，
 * @param <A> 参数
 * @param <V> 返回值
 */
public class Memoizerl <A,V>implements Computable<A,V> {
    private final Map<A,V> cache = new HashMap<>();
    private final Computable<A,V> computable;

    public Memoizerl(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public synchronized V compute(A a) {
        if(Objects.isNull(cache.get(a))){
            cache.put(a,computable.compute(a));
        }
        return cache.get(a);
    }
}
