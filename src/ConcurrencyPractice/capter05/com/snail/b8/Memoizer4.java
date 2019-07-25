package ConcurrencyPractice.capter05.com.snail.b8;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 第三版本，解决第二版本存在的当多个相同的线程同时计算一个结果时，导致重复计算的问题，使用一个线程单独的计算，用FutureTask标识，
 * 这个方法仍然存在概率较小的第二版本的问题，即多个线程计算相同的值，有可能会重复计算，因为计算方法中的if()方法（即符合操作，如果没有则添加）是无法保证原子性的，
 * 所以此处有两种方案：1.对局部代码块进行加锁；2.使用ConcurrentHashMap的putIfAbsent()方法，保证原子性
 * @param <A>
 * @param <V>
 */
public class Memoizer4<A,V> implements Computable<A,V> {
    //自定义缓存
    private final ConcurrentHashMap<A,FutureTask<V>> cache = new ConcurrentHashMap<>();
    //被包装的类型
    private final Computable<A,V> computable;

    public Memoizer4(Computable<A, V> computable) {
        this.computable = computable;
    }

//    /**
//     * 这是自己写的方式1.加锁
//     * @param a
//     * @return
//     * @throws ExecutionException
//     * @throws InterruptedException
//     */
//    @Override
//    public V compute(A a) throws ExecutionException, InterruptedException {
//        synchronized (cache){
//            if(Objects.isNull(cache.get(a))){
//                FutureTask<V> futureTask = new FutureTask<>(()-> {
//                    return computable.compute(a);
//                });
//                new Thread(futureTask).start();
//                cache.put(a,futureTask);
//            }
//        }
//        return cache.get(a).get();
//    }
    /**
     * 这是自己写的方式1.使用ConcurrentHashMap的线程安全的方法
     * @param a
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public V compute(A a){
        FutureTask<V> futureTask = new FutureTask<>(()-> {
            return computable.compute(a);
        });
        FutureTask<V> result = cache.putIfAbsent(a,futureTask);
        //如果返回数据为空，则表示cache中无该参数对应的任务，则新建线程执行该任务。
        if(Objects.isNull(result)){
            new Thread(futureTask).start();
        }
        try {
            return cache.get(a).get();
        } catch (InterruptedException e) {
            //如果检测到异常，则从缓存中移除该计算结果
            cache.remove(a);
            e.printStackTrace();
        } catch (ExecutionException e) {
            cache.remove(a);
            e.printStackTrace();
        }
        return null;
    }
}
