package ThreadPattenByPic.capter12.t1;

import ThreadPattenByPic.capter03.Request;

/**
 * @author xuchuanliangbt
 * @title: ActivationQueue
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/1217:52
 * @Version
 */
public class ActivationQueue {
    /**
     *
     */
    private static final int MAX_METHOS_REQUEST = 100;
    private final MethodRequest[] methodRequests;
    /**
     * 下次putRequest位置
     */
    private int tail;
    /**
     * 下次takeRequest的位置
     */
    private int head;
    /**
     * 实际数量
     */
    private int count;

    public ActivationQueue(){
        this.methodRequests = new MethodRequest[MAX_METHOS_REQUEST];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
    }

    public synchronized void putRequest(MethodRequest request){
        while (count>=MAX_METHOS_REQUEST){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        methodRequests[tail] = request;
        tail = (tail+1) % methodRequests.length;
        count++;
        notifyAll();
    }

    public synchronized MethodRequest takeRequest(){
        while (count<=0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        MethodRequest request = methodRequests[head];
        head = (head+1) % methodRequests.length;
        count--;
        notifyAll();
        return request;
    }
}
