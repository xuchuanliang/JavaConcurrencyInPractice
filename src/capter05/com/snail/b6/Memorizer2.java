package capter05.com.snail.b6;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存V2比缓存V1有更好的并发行为，多线程可并发的使用它，但是若两个线程同时调用compute时存在一个漏洞，可能会导致计算得到相同的值
 * 会带来低效，例如果某个线程启动了一个开销很大的计算，而其他线程并不知道这个计算正在进行，那么很可能会重复这个计算。
 */
public class Memorizer2<A,V> implements Computable<A,V> {

    private final Map<A,V> cahe = new ConcurrentHashMap<>();
    private final Computable<A,V> computable;

    public Memorizer2(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        V result = cahe.get(arg);
        if(null==result){
            result = computable.compute(arg);
            cahe.put(arg,result);
        }
        return result;
    }
}
