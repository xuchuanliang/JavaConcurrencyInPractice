package capter05.com.snail.b6;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存V1,使用传统hashMap配合加锁实现缓存，多线程阻塞，性能和可伸缩性不好
 * @param <A>
 * @param <V>
 */
public class Memoizer1<A,V> implements Computable<A,V> {

    private final Map<A,V> cache = new HashMap<>();
    private final Computable<A,V> computable;

    public Memoizer1(Computable<A,V> computable){
        this.computable = computable;
    }

    /**
     * 防止多个线程同时访问到cache，出现多线程问题，采用传统的加锁方式，能够确保线程安全，但是每次只能有一个线程执行该方法，可伸缩性差
     * @param arg
     * @return
     * @throws InterruptedException
     */
    @Override
    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if(result==null){
            result  = computable.compute(arg);
            cache.put(arg,result);
        }
        return result;
    }
}
