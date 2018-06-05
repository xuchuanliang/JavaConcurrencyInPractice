package capter04.com.snail;

/**
 * Java监视器模式
 */
public class PrivateLock {
    //内置锁
    private final Object myLock = new Object();
    void someMethod(){
        synchronized (myLock){

        }
    }
}
