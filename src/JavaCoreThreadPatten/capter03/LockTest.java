package JavaCoreThreadPatten.capter03;

public class LockTest {
    /**
     * 默认以当前类实例作为锁对象
     */
    public synchronized void innerLock1(){

    }
    private final Object lock = new Object();

    /**
     * 指定锁对象
     */
    public void innerLock2(){
        synchronized (lock){

        }
    }

    /**
     * 默认以当前类的class类实例作为锁对象
     */
    public synchronized static void innerLock3(){

    }
}
