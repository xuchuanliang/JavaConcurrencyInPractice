package JavaCoreThreadPatten.capter03.singleton;

/**
 * 通过内部锁，给类加锁保证getInstance方法是串行，不会出现并发问题，但是这种方式比较影响性能，因为经过第一次创建完成实例后，获取实例仍然需要串行化，并发量高时，压力比较大
 */
public class SingleThreadSingletonV3 {
    private static SingleThreadSingletonV3 singleThreadSingletonV3 = null;
    private SingleThreadSingletonV3(){}

    public static SingleThreadSingletonV3 getInstance(){
        synchronized (SingleThreadSingletonV3.class){
            if(null==singleThreadSingletonV3){
                singleThreadSingletonV3 = new SingleThreadSingletonV3();
            }
            return singleThreadSingletonV3;
        }
    }

    public void someService(){
        //一些处理方法
    }
}
