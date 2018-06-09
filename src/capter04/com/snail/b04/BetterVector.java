package capter04.com.snail.b04;

import capter03.anno.ThreadSafe;

import java.util.Vector;

/**
 * 扩展Vector并增加一个若没有则添加的方法
 * 线程安全，因为和父类使用统一把锁，但是脆弱，当父类的统计机制或者锁改变后，当前类的锁与父类锁不是同一把，则会出现线程问题，因为putIfAbsent这个方法相对于对BetterVector的其他操作就不是原子性了
 * @param <E>
 */
@ThreadSafe
public class BetterVector<E> extends Vector<E> {
    public synchronized boolean putIfAbsent(E x){
        boolean absent = !contains(x);
        if(absent){
            add(x);
        }
        return absent;
    }
}
