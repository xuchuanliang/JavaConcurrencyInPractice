package JavaCoreThreadPatten.capter03;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁降级，先获取写锁，然后获取读锁
 */
public class ReadWriteLockDowngrade {
    private final ReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock();
    private final Lock READ_LOCK = READ_WRITE_LOCK.readLock();
    private final Lock WRITE_LOCK = READ_WRITE_LOCK.writeLock();

    public void operationWithLockDowngrade(){
        boolean readLockAcquired = false;
        WRITE_LOCK.lock();
        try{
            //执行写业务逻辑操作
            System.out.println("执行写操作");
            READ_LOCK.lock();
            readLockAcquired = true;
            //锁降级成功，即获取到读锁
            System.out.println("锁降级成功，即获取到读锁");
        }finally {
            WRITE_LOCK.unlock();
        }
        if(readLockAcquired){
            try{
                //执行读操作
                System.out.println("执行读操作");
            }finally {
                READ_LOCK.unlock();
            }
        }
    }

    public static void main(String[] args){
        ReadWriteLockDowngrade readWriteLockDowngrade = new ReadWriteLockDowngrade();
        readWriteLockDowngrade.operationWithLockDowngrade();
    }
}
