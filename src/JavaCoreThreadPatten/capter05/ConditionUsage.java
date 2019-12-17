package JavaCoreThreadPatten.capter05;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionUsage {
    private static final Lock LOCK = new ReentrantLock();
    private static final Condition CONDITION = LOCK.newCondition();

    public void aGuaredMethod() {
        try {
            LOCK.lock();
            boolean condition = true;
            while (condition) {
                try {
                    CONDITION.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //做一些事情
        } finally {
            LOCK.unlock();
        }
    }

    public void anNotificationMethod(){
        LOCK.lock();
        CONDITION.signal();
    }
}
