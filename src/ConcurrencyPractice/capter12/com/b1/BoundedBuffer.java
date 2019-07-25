package ConcurrencyPractice.capter12.com.b1;

import java.util.concurrent.Semaphore;

/**
 * 基于信号量的有界缓存
 * @param <E>
 */
public class BoundedBuffer<E> {
    /**
     * availableItems 表示可以从缓存中删除的元素个数，他的初始值为0（因为缓存的初始状态为空）
     * availableSpaces 表示可以插入到缓存的元素个数，他的初始值等于缓存大小
     */
    private final Semaphore availableItems,availableSpaces;
    private final E[] items;
    private int putPosition = 0,takePosition = 0;
    public BoundedBuffer(int capacity){
        availableItems = new Semaphore(0);
        availableSpaces = new Semaphore(capacity);
        items = (E[]) new Object[capacity];
    }
    public boolean isEmpty(){
        return availableItems.availablePermits() == 0;
    }
    public boolean isFull(){
        return availableSpaces.availablePermits() == 0;
    }

    public void put(E x) throws InterruptedException {
        availableSpaces.acquire();
        doInsert(x);
        availableItems.release();
    }

    public E taks() throws InterruptedException {
        availableItems.acquire();
        E item = doExtract();
        availableSpaces.release();
        return item;
    }

   private synchronized void doInsert(E x){
        int i = putPosition;
        items[i] = x;
        putPosition = (++i==items.length) ? 0 : i;
   }
   private synchronized E doExtract(){
        int i = takePosition;
        E x = items[i];
        items[i] = null;
        takePosition = (++i==items.length) ? 0 : i;
        return x;
   }
}
