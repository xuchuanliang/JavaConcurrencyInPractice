package JavaCoreThreadPatten.capter05;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * 基于Semaphore的支持流量控制的传输通道实现
 * @param <P>
 */
public class SemaphoreBasedChannel<P>  {
    private final BlockingQueue<P> queue;
    private final Semaphore semaphore;

    /**
     *
     * @param queue 无界队列
     * @param flowlimit 流量数限制
     * @param fair 是否是公平
     */
    public SemaphoreBasedChannel(BlockingQueue<P> queue, int flowlimit,boolean fair) {
        this.queue = queue;
        this.semaphore = new Semaphore(flowlimit,fair);
    }

    public P take(){
        try {
            return queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void put(P product){
        //获取凭证
        try {
            semaphore.acquire();
            queue.put(product);
        } catch (InterruptedException e) {
        }finally {
            //释放凭证
            semaphore.release();
        }
    }

}
