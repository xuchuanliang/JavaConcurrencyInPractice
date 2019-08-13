package ThreadPattenByPic.capter12.t2;

/**
 * @author xuchuanliangbt
 * @title: ActivationQueue
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/1316:21
 * @Version
 */
public class ActivationQueue {
    private final int MAX_LENGTH = 100;
    private final MethodRequest[] methodRequests;
    private int tail;
    private int head;
    private int count;
    public ActivationQueue(){
        this.methodRequests = new MethodRequest[MAX_LENGTH];
        this.tail = 0;
        this.head = 0;
        this.count = 0;
    }

    public synchronized void putRequest(MethodRequest request){
        while (count>=MAX_LENGTH){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        methodRequests[tail++] = request;
        count++;
        tail = tail%MAX_LENGTH;
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
        MethodRequest methodRequest = methodRequests[head++];
        count--;
        head = head%MAX_LENGTH;
        notifyAll();
        return methodRequest;
    }
}
