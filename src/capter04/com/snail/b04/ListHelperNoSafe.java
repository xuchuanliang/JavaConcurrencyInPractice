package capter04.com.snail.b04;

import capter03.anno.NotThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 非线程安全的“若没有则添加”
 * 由于该类的锁和list的锁不是同一个，导致在错误的锁上
 * 主要是因为当前对象锁和list保持线程安全的锁不是同一把，那么putIfAbsent()方法相对于list的其他操作就无法保证原子性，例如在某个线程调用list.add()方法，在另一个线程调用putIfAbsent()方法，那么两个线程上获得的锁不是同一把，就无法保证原子性。
 */
@NotThreadSafe
public class ListHelperNoSafe<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<>());

    public synchronized boolean putIfAbsent(E x){
        boolean absent = !list.contains(x);
        if(absent)
            list.add(x);
        return absent;
    }
}
