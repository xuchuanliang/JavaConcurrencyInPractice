package JavaCoreThreadPatten.capter03;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockUsage {
    private final ReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock();
    private final Lock  READ_LOCK = READ_WRITE_LOCK.readLock();
    private final Lock WRITE_LOCK = READ_WRITE_LOCK.writeLock();

    public boolean read(){
        READ_LOCK.lock();
        try{
            //doSomething()
        }finally {
            READ_LOCK.unlock();
        }
        return true;
    }

    public boolean write(){
        WRITE_LOCK.lock();
        try{
            //doSomething()
        }finally {
            WRITE_LOCK.unlock();
        }
        return false;
    }
}
