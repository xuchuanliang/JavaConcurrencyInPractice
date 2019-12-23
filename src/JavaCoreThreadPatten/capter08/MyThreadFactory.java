package JavaCoreThreadPatten.capter08;

import java.util.Objects;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 自定义线程工厂，在创建线程的过程中，指定线程的异常终结处理方法
 */
public class MyThreadFactory implements ThreadFactory {
    /**
     * 线程名称前缀
     */
    private String namePrefix;
    /**
     * 记录线程数量
     */
    private final AtomicLong atomicLong = new AtomicLong(1);
    public MyThreadFactory (String namePrefix){
        this.namePrefix = namePrefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = doMakeThread(r);
        t.setUncaughtExceptionHandler(new ExceptionCause());
        if(t.isDaemon()){
            t.setDaemon(false);
        }
        if(t.getPriority() != Thread.NORM_PRIORITY){
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }

    protected Thread doMakeThread(Runnable r){
        Thread t = new Thread(r,"Thread "+atomicLong.getAndIncrement()+" From MyThreadFactory"){
            @Override
            public String toString() {
                ThreadGroup threadGroup = this.getThreadGroup();
                String groupName = Objects.nonNull(threadGroup) ? threadGroup.getName() : "";

                return "Thread:"+groupName+" "+this.getName()+" from MyThreadFactory";
            }
        };
        return t;
    }

    /**
     * 线程异常终结处理类
     */
    private class ExceptionCause implements Thread.UncaughtExceptionHandler{

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            //记录线程异常终结的错误信息
            System.err.println("线程："+t+"----异常终结");
        }
    }
}
