package JavaCoreThreadPatten.capter03.singleton;

/**
 * 可用版本
 * 单例：饿汉模式
 * 不存在线程安全问题，但是程序启动即创建实例，可能存在用不到的情况，浪费空间
 */
public class SingleThreadSingletonV2 {
    private static SingleThreadSingletonV2 singleThreadSingletonV2 = new SingleThreadSingletonV2();

    private SingleThreadSingletonV2(){}

    public static SingleThreadSingletonV2 getInstance(){
        return singleThreadSingletonV2;
    }

    public void someService(){
        //一些处理方法
    }
}
