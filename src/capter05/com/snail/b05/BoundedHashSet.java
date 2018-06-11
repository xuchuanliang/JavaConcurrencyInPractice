package capter05.com.snail.b05;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 使用Semphore为容器设置边界
 * @param <T>
 */
public class BoundedHashSet<T> {

    private final Set<T> set;
    private final Semaphore semaphore;

    public BoundedHashSet(int bound){
        set = Collections.synchronizedSet(new HashSet<>());
        semaphore = new Semaphore(bound);
    }

    /**
     * 增加某个元素
     * @param t
     * @return
     * @throws InterruptedException
     */
    public boolean add(T t) throws InterruptedException {
        //获取许可，若无许可，则会一直阻塞至有许可
        semaphore.acquire();
        boolean wasAdd = false;
        try{
            wasAdd = set.add(t);
            return wasAdd;
        }finally {
            if(!wasAdd){
                semaphore.release();
            }
        }
    }

    /**
     * 删除某个元素，若删除则释放许可
     * @param o
     * @return
     */
    public boolean remove(Object o){
        boolean wasRemoved = set.remove(o);
        if(wasRemoved)
            semaphore.release();
        return wasRemoved;
    }

}
