package JavaCoreThreadPatten.capter05;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 可优雅中断的任务处理器
 */
public class TerminatableTaskRunnable<T extends Runnable> {
    protected final BlockingQueue<T> queue;
    private Thread workThread;
    //表示是否需要中断，true表示不需要中断
    private volatile boolean isUse = true;
    //表示当前需要进行的任务数量
    private final AtomicLong taskNum = new AtomicLong(0);


    public TerminatableTaskRunnable(BlockingQueue<T> queue) {
        this.queue = queue;
        this.workThread = new WorkThred();
    }

    public void init(){
        workThread.start();
    }

    /**
     * 当没有中断的情况下，继续接受任务
     * @param task
     * @throws InterruptedException
     */
    public void submit(T task) throws InterruptedException {
        if(isUse){
            queue.put(task);
            taskNum.incrementAndGet();
        }else {
            System.err.println("拒绝接受任务");
        }
    }

    /**
     * 中断线程
     */
    public void shutdown(){
        isUse = false;
        //唤醒线程，检查中断标识
        workThread.interrupt();
    }


    class WorkThred extends Thread{
        @Override
        public void run() {
            for(;;){
                //中断且当前待执行的任务书为0
                if(!isUse && taskNum.get()<=0){
                    break;
                }
                try {
                    queue.take().run();
                } catch (InterruptedException e) {
                }
                taskNum.decrementAndGet();
            }
        }
    }
}
