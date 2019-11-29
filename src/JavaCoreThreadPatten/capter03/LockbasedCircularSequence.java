package JavaCoreThreadPatten.capter03;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockbasedCircularSequence {
    private short sequence = 1;
    private static final Lock LOCK = new ReentrantLock();

    public short sequence(){
        LOCK.lock();
        try{
            if(sequence >= 99){
                sequence = 0;
            }else{
                sequence++;
            }
            return sequence;
        }finally {
            LOCK.unlock();
        }
    }
}
