package JavaCoreThreadPatten.capter07;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.TimeUnit;

/**
 * 死锁检测器
 */
public class DeadlockDetector extends Thread{
    private final long time;
    private static final ThreadMXBean THREAD_MX_BEAN = ManagementFactory.getThreadMXBean();

    public DeadlockDetector(long time) {
        setDaemon(true);
        this.time = time;
    }

    @Override
    public void run() {
        for(;;){
            long[] deadlockedThreads = THREAD_MX_BEAN.findDeadlockedThreads();
            if(null!=deadlockedThreads && deadlockedThreads.length>0){
                for(long l:deadlockedThreads){
                    ThreadInfo threadInfo = THREAD_MX_BEAN.getThreadInfo(l);
                    System.err.println("死亡线程："+threadInfo);
                }
            }
            try {
                TimeUnit.SECONDS.sleep(time);
            } catch (InterruptedException e) {
            }
        }
    }
}
