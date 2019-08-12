package ThreadPattenByPic.capter11.t2;

/**
 * @author xuchuanliangbt
 * @title: Log
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/1212:30
 * @Version
 */
public class Log {
    private static final ThreadLocal<TSLog> threadLocal = new ThreadLocal<>();

    public static void print(String s){
        getLog().log(s);
    }

    private static TSLog getLog(){
        TSLog log = threadLocal.get();
        if(null==log){
            log = new TSLog(Thread.currentThread().getName());
            threadLocal.set(log);
        }
        return log;
    }

}
