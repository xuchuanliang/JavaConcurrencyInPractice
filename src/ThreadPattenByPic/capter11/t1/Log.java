package ThreadPattenByPic.capter11.t1;

/**
 * @author xuchuanliangbt
 * @title: Log
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/1211:53
 * @Version
 */
public class Log {
    public static void log(Object s){
        System.out.println(Thread.currentThread().getName() +"====" + (null!=s ? s:"null"));
    }
    public static void logClose(){
        System.out.println("close log");
    }
}
