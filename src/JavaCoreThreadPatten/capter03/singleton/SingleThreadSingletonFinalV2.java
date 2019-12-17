package JavaCoreThreadPatten.capter03.singleton;

/**
 * 可用版本：
 * 通过内部类的方式，同样也是延迟初始化
 *
 */
public class SingleThreadSingletonFinalV2 {
    private SingleThreadSingletonFinalV2 (){}

    private static class INSTANCE_HOLDER{
        final static SingleThreadSingletonFinalV2 SINGLE_THREAD_SINGLETON_FINAL_V_2 = new SingleThreadSingletonFinalV2();
    }

    public static SingleThreadSingletonFinalV2 getInstance(){
        return INSTANCE_HOLDER.SINGLE_THREAD_SINGLETON_FINAL_V_2;
    }

    public void someService(){
        //一些处理方法
    }
}
