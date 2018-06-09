package capter04.com.snail.b04;

import capter03.anno.ThreadSafe;

import java.util.List;

/**
 * 通过组合实现若没有则添加
 *此类时线程安全的，因为首先没有将list暴露出来，其次声明为final类型，也就是初始化完成后不能修改引用，同时通过自身内置锁来保持原子性，实现线程安全，只要保持在类中拥有指向底层List的唯一外部引用，就能确保线程安全性
 * @param <E>
 */
@ThreadSafe
public class ImprovedList<E> {
    private final List<E> list;
    public ImprovedList(List<E> list){
        this.list = list;
    }
    public synchronized boolean putIfAbsent(E x){
        boolean contains = list.contains(x);
        if(contains){
            list.add(x);
        }
        return contains;
    }
}
