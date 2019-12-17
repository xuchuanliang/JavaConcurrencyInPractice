package JavaCoreThreadPatten.capter03.cas;

import java.util.concurrent.atomic.AtomicLong;

/*
    使用CAS实现线程安全的计数器
    一般我们直接使用java提供的如AtomicLong等
 */
public class CASBasederCounter {
    private volatile long count;
    public long value(){
        return count;
    }
    public void increment(){
        long oldValue;
        long newValue;
        do{
            oldValue = count;
            newValue = oldValue+1;
        }while (/*调用CAS来更新变量的值*/!compareAndSwap(oldValue,newValue));
    }

    private boolean compareAndSwap(long oldValue, long newValue) {
        //
        return true;
    }
}
