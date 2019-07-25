package ConcurrencyPractice.capter04.com.snail.b04;

import ConcurrencyPractice.capter03.anno.ThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *通过客户端加锁来实现“若没有则添加”
 * 此处时线程安全的，主要是synchronized (list)这把锁和list内部的锁是统一把，实现了原子性操作
 */
@ThreadSafe
public class ListHelperSafe<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<>());

    public boolean putIfAbsent(E x){
        synchronized (list){
            boolean absent = !list.contains(x);
            if(absent){
                list.add(x);
            }
            return absent;
        }
    }
}
