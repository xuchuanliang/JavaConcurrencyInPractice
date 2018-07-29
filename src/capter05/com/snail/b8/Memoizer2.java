package capter05.com.snail.b8;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

/**
 * 缓存第二个版本，使用ConcurrentMap代替传统加锁方式，提高并发性
 * 但是存在第二个问题，当多个线程并发的对同一个参数进行计算时，导致计算出相同的结果，比较低效
 * 此时实际上是当计算时，首先查看当前参数有没有已经进行计算或者已经有了结果，如果已经计算或者已经有了结果，那么等待或使用现成的结果就好，此处使用FutureTask代表一个任务比较合适
 * @param <A>
 * @param <V>
 */
public class Memoizer2<A,V> implements Computable<A,V> {
    private final ConcurrentHashMap<A,V> concurrentHashMap = new ConcurrentHashMap<>();
    private final Computable<A,V> computable;

    public Memoizer2(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(A a) throws ExecutionException, InterruptedException {
        if(Objects.isNull(concurrentHashMap.get(a))){
            concurrentHashMap.put(a,computable.compute(a));
        }
        return concurrentHashMap.get(a);
    }
}
