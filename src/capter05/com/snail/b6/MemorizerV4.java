package capter05.com.snail.b6;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * 缓存V4
 */
public class MemorizerV4<A,V> implements Computable<A,V>{
    private final ConcurrentMap<A,Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A,V> computable;

    public MemorizerV4(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        while (true){
            Future<V> f = cache.get(arg);
            if(Objects.isNull(f)){
                Callable<V> eval = new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return computable.compute(arg);
                    }
                };
                FutureTask<V> ft = new FutureTask<>(eval);
                f = cache.putIfAbsent(arg,ft);
                if(f==null){
                    f = ft;
                    ft.run();
                }
            }
            try {
                return f.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
