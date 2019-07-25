package ConcurrencyPractice.capter05.com.snail.b6;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

/**
 *缓存V3.0  通过futureTask解决V2中存在并发时多次相同计算问题
 * 但是由于判断task是否为空没有加锁，是典型的先判断，后执行操作，有可能依然存在并发行问题
 * @param <A>
 * @param <V>
 */
public class MemorizerV3<A,V> implements Computable<A,V> {

    private final Map<A,Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A,V> computable;

    public MemorizerV3(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        Future<V> task = cache.get(arg);
        if(Objects.isNull(task)){
            Callable<V> callable = new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return computable.compute(arg);
                }
            };
            FutureTask<V> futureTask = new FutureTask<>(callable);
            task = futureTask;
            cache.put(arg,task);
            futureTask.run();
        }
        try {
            return task.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
