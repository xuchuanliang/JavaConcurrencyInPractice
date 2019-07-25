package ThreadPattenByPic.capter03;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 模拟队列
 */
public class RequestQueue {
    /**
     * LinkedList是线程非安全的队列
     */
    private final Queue<Request> queue = new LinkedList<>();
    public synchronized Request getRequest(){
        while (queue.peek() == null){
            try{
                wait();
            }catch (InterruptedException e){}
        }
        return queue.remove();
    }

    public synchronized void putRequest(Request request){
        queue.offer(request);
        notifyAll();
    }
}
