package ThreadPattenByPic.capter03;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author xuchuanliangbt
 * @title: RequestQueue2
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/2514:31
 * @Version
 */
public class RequestQueue2 {
    /**
     * 线程安全的队列
     */
    private final BlockingQueue<Request> queue = new LinkedBlockingQueue<>();
    public Request getRequest(){
        try {
            return queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void putRequest(Request request){
        try {
            queue.put(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
