package JavaCoreThreadPatten.capter03;

/**
 * 单例模式
 */
public class SingleThreadSingleton {
}

class  T1{
    /**
     * 使用volatile关键字是为了方式锁内部重排序，导致获取到的对象引用是一个未初始化完成的对象
     */
    private volatile static T1 instance = null;
    private T1(){}
    public static T1 getInstance(){
        if(instance==null){
            synchronized (SingleThreadSingleton.class){
                if(instance==null){
                    instance = new T1();
                }
            }
        }
        return instance;
    }
}

class T2{
    private T2(){}

    private static class InstanceHolder{
        final static T2 INSTANCE = new T2();
    }

    public static T2 getInstance(){
        return InstanceHolder.INSTANCE;
    }
    public void someService(){
    }

    public static void main(String[] args){
        T2.getInstance();
    }
}

enum T3{
    INSTANCE;
    T3(){}
    public void someService(){

    }
    public static void main(String[] args){
        T3.INSTANCE.someService();
    }
}
