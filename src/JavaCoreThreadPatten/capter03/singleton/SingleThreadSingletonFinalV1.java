package JavaCoreThreadPatten.capter03.singleton;

/**
 * 可用版本：
 * 单例模式最终版本：双重检查锁+volatile
 */
public class SingleThreadSingletonFinalV1 {
    private static volatile SingleThreadSingletonFinalV1 singleThreadSingletonFinal = null;
    private SingleThreadSingletonFinalV1(){}

    public static SingleThreadSingletonFinalV1 getInstance(){
        if(null==singleThreadSingletonFinal){
            synchronized (SingleThreadSingletonFinalV1.class){
                if(null==singleThreadSingletonFinal){
                    singleThreadSingletonFinal = new SingleThreadSingletonFinalV1();
                }
            }
        }
        return singleThreadSingletonFinal;
    }

    public void someService(){
        //一些处理方法
    }
}
