package JavaCoreThreadPatten.capter03.singleton;

/**
 * 单例模式，含多线程安全问题
 * 懒汉模式
 */
public class SingleThreadedSingletonV1 {
    public static SingleThreadedSingletonV1 singleThreadedSingletonV1 = null;
    private SingleThreadedSingletonV1(){}

    /**
     * 方法体中实际上是check-then-act模式操作，存在线程安全问题
     * @return
     */
    public static SingleThreadedSingletonV1 getInstance(){
        if(singleThreadedSingletonV1 == null){
            singleThreadedSingletonV1 = new SingleThreadedSingletonV1();
        }
        return singleThreadedSingletonV1;
    }

    public void someService(){
        //一些处理方法
    }
}
