package JavaCoreThreadPatten.capter03.singleton;

/**
 * 通过双重检查锁的方式既保证了并发情况下不会创建多次，同时也考虑的并发情况下性能问题
 * 但是仍然存在问题，解释如下：
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
public class SingleThreadSingletonV4 {
    private static SingleThreadSingletonV4 singleThreadSingletonV4 = null;
    private SingleThreadSingletonV4 (){}

    public static SingleThreadSingletonV4 getInstance(){
        if(null==singleThreadSingletonV4){
            synchronized (SingleThreadSingletonV4.class){
                if(null==singleThreadSingletonV4){
                    singleThreadSingletonV4 = new SingleThreadSingletonV4();
                }
            }
        }
        return singleThreadSingletonV4;
    }

    public void someService(){
        //一些处理方法
    }
}
