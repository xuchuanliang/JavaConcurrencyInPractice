package JavaCoreThreadPatten.capter03;

/**
 * 使用volatile实现读写锁
 */
public class Counter {
    private volatile long count;

    public long value(){
        return count;
    }

    public void increment(){
        synchronized (this){
            count++;
        }
    }
}
