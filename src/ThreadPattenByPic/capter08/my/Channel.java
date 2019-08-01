package ThreadPattenByPic.capter08.my;

/**
 * @author xuchuanliangbt
 * @title: Channel
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/3117:52
 * @Version
 */
public class Channel {
    private final Request[] requests;
    private final WorkerThread[] workerThreads;
    private static final int MAX_REQUEST = 100;
    /**
     * 下次putrequest位置
     */
    private int tail;
    /**
     * 下次takerequest位置
     */
    private int head;
    /**
     * request数量
     */
    private int count;

    public Channel(int workNum){
        tail = 0;
        head = 0;
        count = 0;
        requests = new Request[MAX_REQUEST];
        workerThreads = new WorkerThread[workNum];
        for(int i=0;i<workNum;i++){
            workerThreads[i] = new WorkerThread("WORKER "+i,this);
        }
    }

    public void startWork(){
        for(int i=0;i<workerThreads.length;i++){
            workerThreads[i].start();
        }
    }

    public synchronized void putRequest(Request request) throws InterruptedException {
        //只要超过了最大数量的请求，则等待
        while (count>=MAX_REQUEST){
            wait();
        }
        count++;
        requests[tail] = request;
        tail = (tail+1) % MAX_REQUEST;
        notifyAll();
    }

    public synchronized Request takeRequest() throws InterruptedException {
        while (count<=0){
            wait();
        }
        count--;
        Request request = requests[head];
        head = (head+1) % MAX_REQUEST;
        notifyAll();
        return request;
    }
}
