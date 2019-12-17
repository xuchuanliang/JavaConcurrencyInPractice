package JavaCoreThreadPatten.capter05;

import java.util.concurrent.BlockingQueue;

public class TaskRunner {
    protected final BlockingQueue<Runnable> blockingQueue;
    protected volatile Thread workerThread;

    public TaskRunner(BlockingQueue<Runnable> blockingQueue) {
        this.blockingQueue = blockingQueue;
        this.workerThread = new WorkThread();
    }

    public void init(){
        workerThread.start();
    }

    public void submit(Runnable task){
        try {
            blockingQueue.put(task);
        } catch (InterruptedException e) {
        }
    }

    class WorkThread extends Thread{
        @Override
        public void run() {
            for(;;){
                try {
                    blockingQueue.take().run();
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
